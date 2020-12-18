/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.impl;

import com.google.gson.Gson;
import com.ibot.datawsdl.soapxml.APIRechargeService;
import com.ibot.library.Security;
import com.ibot.library.ValidData;
import com.ibot.module.base.entities.ApiResultModel;
import com.ibot.module.entities.ApiTopupModel;
import com.ibot.module.entities.CardTransaction;
import com.ibot.module.entities.CardDepositConfig;
import com.ibot.module.entities.TopupModel;
import com.ibot.module.type.OptionListModel;
import com.ibot.module.type.CardProvider;
import com.ibot.notifization.JsonResult;
import com.ibot.notifization.MessageText;
import com.ibot.notifization.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibot.module.type.EnumService;
import com.ibot.module.services.ICardTranstionService;
import com.ibot.module.repository.CardTransactionRepository;
import com.ibot.module.repository.CardDepositConfigRepository;
import com.ibot.module.services.ICardHistoryService;

/**
 *
 * @author Allen
 */
@Service
public class CardTransactionImpl implements ICardTranstionService {

    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    @Autowired
    private CardDepositConfigRepository cardDepositConfigRepository;

    @Autowired
    private ICardHistoryService cardHistoryService;

    @Override
    public JsonResult Topup(TopupModel model) {
        //
        if (model == null) {
            return Notification.Invalid(MessageText.Invalid);
        }
        //  model
        String gameCode = model.gameCode;
        String loginId = model.loginId;
        int cardType = model.cardType;
        int cardValue = model.cardValue;
        String cardCode = model.cardCode;
        String cardSerial = model.cardSerial;

        //
        if (!ValidData.patternRoll.matcher(cardSerial).matches()) {
            return Notification.Invalid("Mã Serial thẻ nạp không hợp lệ");
        }
        //
        if (!ValidData.patternRoll.matcher(cardCode).matches()) {
            return Notification.Invalid("Mã thẻ nạp không hợp lệ");
        }
        //
        if (cardValue <= 0) {
            return Notification.Invalid("Giá trị thẻ nạp không hợp lệ");
        }
        //
        CardTransaction depositCard = cardTransactionRepository.findbySerial(cardSerial, cardType);
        if (depositCard != null) {
            return Notification.Invalid("Thẻ nạp đã được sử dụng");
        }
        //
        CardProvider internetProvider = new CardProvider();
        OptionListModel intProviderOption = internetProvider.CardTypeData.stream().filter(m -> m.id == cardType).findAny().orElse(null);
        //
        if (intProviderOption == null) {
            return Notification.Invalid(MessageText.Invalid);
        }
        //
        CardDepositConfig depositConfig = cardDepositConfigRepository.findbyGameCode(gameCode);
        if (depositConfig == null) {
            return Notification.Invalid("Cổng thanh toán chưa được cấu hình");
        }
        String partnerCode = depositConfig.getPartnerCode();
        ApiResultModel result;
        switch (partnerCode) {
            case "C001": // COMP 01
                result = comp01Topup(model);
                break;
            case "C002": // COMP 02
                result = comp02Topup(model);
                break;
            case "C003": // COMP 03
                result = comp03Topup(model);
                break;
            default:
                result = new ApiResultModel(EnumService.APIPartnerEnum.NONE, -1, MessageText.NotService);
                break;
        }
        // 
        EnumService.APIPartnerEnum aPICompProviderEnum = result.getCompEnum();
        if (aPICompProviderEnum == EnumService.APIPartnerEnum.NONE) {
            return Notification.NotFound("Không tìm thấy nhà cung cấp dịch vụ");
        }
        if (aPICompProviderEnum == EnumService.APIPartnerEnum.COMP01) {
            if (result.getCode() != 1) {
                return Notification.Invalid(result.getMessage());
            }
        }
        if (aPICompProviderEnum == EnumService.APIPartnerEnum.COMP02) {
            if (result.getCode() != 1) {
                return Notification.Error(result.getMessage());
            }
        }
        if (aPICompProviderEnum == EnumService.APIPartnerEnum.COMP03) {
            if (result.getCode() != 1) {
                return Notification.Error(result.getMessage());
            }
        }
        //
        int transactionStatus = result.getCode();
        // save data 
        CardTransaction cardTransaction = new CardTransaction();
        cardTransaction.setPartnerCode(partnerCode);
        cardTransaction.setLoginId(loginId);
        cardTransaction.setCardType(cardType);
        cardTransaction.setCardValue(cardValue);
        cardTransaction.setCardCode(cardCode);
        cardTransaction.setCardSerial(cardSerial);
        cardTransaction.setTransactionStatus(transactionStatus);
        CardTransaction depositRs = this.cardTransactionRepository.save(cardTransaction);
        if (depositRs == null) {
            // log error system
            System.out.println("Error: lỗi lưu data");
        }
        // history 

        //CardDepositHistoryImpl cardDepositHistoryImpl = new CardDepositHistoryImpl();
        cardHistoryService.loggedCardDepositHistory(
                "Gd nạp tiền cổng thanh toán: " + gameCode + ".",
                "",
                depositRs.getId(),
                loginId,
                cardValue
        );

        // 
        Gson gson = new Gson();
        String jsonResult = gson.toJson(result);
        return Notification.Success(MessageText.Success + "::" + jsonResult);
    }

    private ApiResultModel comp01Topup(TopupModel model) {
        int cardType = model.cardType;
        int cardValue = model.cardValue;
        String cardCode = model.cardCode;
        String cardSerial = model.cardSerial;

        String partnerName = "T-PartName01";
        String password = "T-Password";
        String secretkey = "T-Secretkey";

        CardProvider cardProvider = new CardProvider();
        OptionListModel cardOption = cardProvider.CardTypeData.stream().filter(m -> m.id == cardType).findAny().orElse(null);
        //
        String cardtype = cardOption.code;
        String signatureHash = Security.getMd5(cardtype + cardCode + cardSerial + partnerName + password + secretkey);
        // create param
        ApiTopupModel apiCardDeposit = new ApiTopupModel();
        apiCardDeposit.partnerName = partnerName;
        apiCardDeposit.password = password;
        apiCardDeposit.secretkey = secretkey;
        apiCardDeposit.cardtype = cardtype; // VT or MOBILE or VINA
        apiCardDeposit.cardValue = cardValue;
        apiCardDeposit.cardSerial = cardSerial;
        apiCardDeposit.cardCode = cardCode;
        apiCardDeposit.signature = signatureHash;
        // string param
        Gson gson = new Gson();
        String jsonParam = gson.toJson(apiCardDeposit);
        // call api service
        APIRechargeService xmlHelper = new APIRechargeService();
        ApiResultModel jsonRqModel = xmlHelper.apiCom01_Topup(jsonParam);
        return jsonRqModel;
    }

    private ApiResultModel comp02Topup(TopupModel model) {
        return new ApiResultModel(EnumService.APIPartnerEnum.COMP02, -1, MessageText.NotService);
    }

    private ApiResultModel comp03Topup(TopupModel model) {
        return new ApiResultModel(EnumService.APIPartnerEnum.COMP03, -1, MessageText.NotService);
    }

}
