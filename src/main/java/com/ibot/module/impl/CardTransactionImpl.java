/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ibot.datawsdl.soapxml.ApiRechargeService;
import com.ibot.library.Security;
import com.ibot.library.ValidData;
import com.ibot.module.base.entities.ApiResultModel;
import com.ibot.module.entities.Api01TopupRequest;
import com.ibot.module.entities.Api01TopupResult;
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
import org.springframework.http.HttpStatus;

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
        if (cardSerial == null || "".equals(cardSerial) || !ValidData.patternRoll.matcher(cardSerial).matches()) {
            return Notification.Invalid("Mã Seri thẻ nạp không hợp lệ");
        }
        //
        if (cardCode == null || "".equals(cardCode) || !ValidData.patternRoll.matcher(cardCode).matches()) {
            return Notification.Invalid("Mã thẻ nạp không hợp lệ");
        }
        //
        if (cardValue <= 0) {
            return Notification.Invalid("Giá trị thẻ nạp không hợp lệ");
        }
        //
        CardTransaction cardTransactionCheck = cardTransactionRepository.validCard(cardSerial, cardCode, cardType);
        if (cardTransactionCheck != null) {
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
        if (result.getCode() != HttpStatus.OK.value()) {
            return Notification.Error(result.getMessage());
        }
        // 
        CardTransaction cardTransaction = new CardTransaction();
        cardTransaction.setPartnerCode(partnerCode);
        cardTransaction.setLoginId(loginId);
        cardTransaction.setCardType(cardType);
        cardTransaction.setCardValue(cardValue);
        cardTransaction.setCardCode(cardCode);
        cardTransaction.setCardSerial(cardSerial);
        cardTransaction.setTransactionStatus(0);
        //
        EnumService.APIPartnerEnum aPICompProviderEnum = result.getCompEnum();
        if (aPICompProviderEnum == EnumService.APIPartnerEnum.NONE) {
            return Notification.NotFound("Không tìm thấy nhà cung cấp dịch vụ");
        }
        if (aPICompProviderEnum == EnumService.APIPartnerEnum.COMP01) {
            Api01TopupResult api01TopupResult = (Api01TopupResult) result.getData();
            // save data
            TransactionSave(cardTransaction, gameCode);
            return Notification.Success(result.getMessage(), api01TopupResult);
        }
        if (aPICompProviderEnum == EnumService.APIPartnerEnum.COMP02) {
            Api01TopupResult api01TopupResult = (Api01TopupResult) result.getData();
            // save data
            TransactionSave(cardTransaction, gameCode);
            return Notification.Success(result.getMessage(), api01TopupResult);
        }
        if (aPICompProviderEnum == EnumService.APIPartnerEnum.COMP03) {
            Api01TopupResult api01TopupResult = (Api01TopupResult) result.getData();
            // save data
            TransactionSave(cardTransaction, gameCode);
            return Notification.Success(result.getMessage(), api01TopupResult);
        }
        System.out.println("Error: lỗi lưu data");
        return Notification.Error(result.getMessage());
    }

    private ApiResultModel comp01Topup(TopupModel model) {
        // model
        String requestId = model.requestId;
        int cardType = model.cardType;
        int cardValue = model.cardValue;
        String cardCode = model.cardCode;
        String cardSerial = model.cardSerial;
        //
        String partnerName = "sieutoc";
        String password = "Abc9999@";
        String apiKey = "5d3ea0e9-56e0-46f3-bab8-b353c4841698";
        CardProvider cardProvider = new CardProvider();
        OptionListModel cardOption = cardProvider.CardTypeData.stream().filter(m -> m.id == cardType).findAny().orElse(null);
        //
        String cardtype = cardOption.code;
        String signature = Security.getMd5(apiKey + cardValue + cardCode + cardSerial);
        // create param
        Api01TopupRequest api01TopupRequest = new Api01TopupRequest();
        api01TopupRequest.api_key = apiKey;
        api01TopupRequest.card_seri = cardSerial;
        api01TopupRequest.card_code = cardCode;
        api01TopupRequest.request_id = requestId;
        api01TopupRequest.card_amount = cardValue;
        api01TopupRequest.card_type = cardtype;
        api01TopupRequest.signature = signature;
        // call api
        ApiRechargeService apiRechargeService = new ApiRechargeService();

        Api01TopupResult api01TopupResult = apiRechargeService.apiCom01_Topup(api01TopupRequest);
        if (api01TopupResult != null) {
            return new ApiResultModel(EnumService.APIPartnerEnum.COMP01, HttpStatus.OK.value(), MessageText.Success, api01TopupResult);
        }
        return new ApiResultModel(EnumService.APIPartnerEnum.COMP01, HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageText.NotService);
    }
//    private ApiResultModel comp01Topup(TopupModel model) {
//        int cardType = model.cardType;
//        int cardValue = model.cardValue;
//        String cardCode = model.cardCode;
//        String cardSerial = model.cardSerial;
//
//        String partnerName = "T-PartName01";
//        String password = "T-Password";
//        String secretkey = "T-Secretkey";
//
//        CardProvider cardProvider = new CardProvider();
//        OptionListModel cardOption = cardProvider.CardTypeData.stream().filter(m -> m.id == cardType).findAny().orElse(null);
//        //
//        String cardtype = cardOption.code;
//        String signatureHash = Security.getMd5(cardtype + cardCode + cardSerial + partnerName + password + secretkey);
//        // create param
//        Api01TopupRequest apiCardDeposit = new Api01TopupRequest();
//        apiCardDeposit.partnerName = partnerName;
//        apiCardDeposit.password = password;
//        apiCardDeposit.secretkey = secretkey;
//        apiCardDeposit.cardtype = cardtype; // VT or MOBILE or VINA
//        apiCardDeposit.cardValue = cardValue;
//        apiCardDeposit.cardSerial = cardSerial;
//        apiCardDeposit.cardCode = cardCode;
//        apiCardDeposit.signature = signatureHash;
//        // string param
//        Gson gson = new Gson();
//        String jsonParam = gson.toJson(apiCardDeposit);
//        // call api service
//        ApiRechargeService xmlHelper = new ApiRechargeService();
//        ApiResultModel jsonRqModel = xmlHelper.apiCom01_Topup(jsonParam);
//        return jsonRqModel;
//    }

    private ApiResultModel comp02Topup(TopupModel model) {
        return new ApiResultModel(EnumService.APIPartnerEnum.COMP02, -1, MessageText.NotService);
    }

    private ApiResultModel comp03Topup(TopupModel model) {
        return new ApiResultModel(EnumService.APIPartnerEnum.COMP03, -1, MessageText.NotService);
    }
    // SAVE DATA TO DB#############################################################################################################

    private boolean TransactionSave(CardTransaction model, String gameCode) {
        CardTransaction cardTransaction = new CardTransaction();
        cardTransaction.setPartnerCode(model.getPartnerCode());
        cardTransaction.setLoginId(model.getLoginId());
        cardTransaction.setCardType(model.getCardType());
        cardTransaction.setCardValue(model.getCardValue());
        cardTransaction.setCardCode(model.getCardCode());
        cardTransaction.setCardSerial(model.getCardSerial());
        cardTransaction.setTransactionStatus(model.getTransactionStatus());
        CardTransaction depositRs = this.cardTransactionRepository.save(cardTransaction);
        if (depositRs != null) {
            // log error system 
            String transId = depositRs.getId();
            cardHistoryService.loggedCardDepositHistory(
                    "Nạp tiền cổng thanh toán: " + gameCode + ".",
                    "",
                    transId,
                    model.getLoginId(),
                    model.getCardValue()
            );
            return true;
        }
        return false;
    }
}
