/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.deposit.Impl;

import com.google.gson.Gson;
import com.ibot.datawsdl.soapxml.APIRechargeService;
import com.ibot.library.Security;
import com.ibot.library.ValidData;
import com.ibot.module.base.entities.ApiResultModel;
import com.ibot.module.deposit.entities.ApiDepositModel;
import com.ibot.module.deposit.entities.DepositCard;
import com.ibot.module.deposit.entities.DepositConfig;
import com.ibot.module.deposit.entities.DepositTopupModel;
import com.ibot.module.deposit.repository.DepositConfigRepository;
import com.ibot.module.deposit.repository.DepositRepository;
import com.ibot.module.deposit.services.IDepositService;
import com.ibot.module.type.OptionListModel;
import com.ibot.module.type.CardProvider;
import com.ibot.notifization.JsonResult;
import com.ibot.notifization.MessageText;
import com.ibot.notifization.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibot.module.type.EnumService;

/**
 *
 * @author Allen
 */
@Service
public class DepositImpl implements IDepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private DepositConfigRepository depositConfigRepository; 
    
    @Override
    public JsonResult Topup(DepositTopupModel model) {
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
        DepositCard depositCard = depositRepository.findbySerial(cardSerial, cardType);
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
        DepositConfig depositConfig = depositConfigRepository.findbyGameCode(gameCode);
        if (depositConfig == null) {
            return Notification.Invalid("Mã game chưa được cấu hình");
        }
        int compProviderId = depositConfig.getCompProviderID();
        ApiResultModel result;
        switch (depositConfig.getCompProviderID()) {
            case 1: // COMP 01
                result = comp01Topup(model);
                break;
            case 2: // COMP 02
                result = comp02Topup(model);
                break;
            case 3: // COMP 03
                result = comp03Topup(model);
                break;
            default:
                result = new ApiResultModel(EnumService.APICompProviderEnum.NONE, -1, MessageText.NotService);
                break;
        }
        // 
        EnumService.APICompProviderEnum aPICompProviderEnum = result.getCompEnum();
        if (aPICompProviderEnum == EnumService.APICompProviderEnum.NONE) {
            return Notification.NotFound("Không tìm thấy nhà cung cấp dịch vụ");
        }
        if (aPICompProviderEnum == EnumService.APICompProviderEnum.COMP01) {
            if (result.getCode() != 1) {
                return Notification.Invalid(result.getMessage());
            }
        }
        if (aPICompProviderEnum == EnumService.APICompProviderEnum.COMP02) {
            if (result.getCode() != 1) {
                return Notification.Error(result.getMessage());
            }
        }
        if (aPICompProviderEnum == EnumService.APICompProviderEnum.COMP03) {
            if (result.getCode() != 1) {
                return Notification.Error(result.getMessage());
            }
        }
        //
        int transactionStatus = result.getCode();
        // save data 
        DepositCard deposit = new DepositCard();
        deposit.setCompProviderID(compProviderId);
        deposit.setLoginId(loginId);
        deposit.setCardType(cardType);
        deposit.setCardValue(cardValue);
        deposit.setCardCode(cardCode);
        deposit.setCardSerial(cardSerial);
        deposit.setTransactionStatus(transactionStatus);
        DepositCard depositRs = this.depositRepository.save(deposit);
        if (depositRs == null) {
            // log error system
            System.out.println("Error: lỗi lưu data");
        }
        // 
        Gson gson = new Gson();
        String jsonResult = gson.toJson(result);
        return Notification.Success(MessageText.Success + "::" + jsonResult);
    }

    private ApiResultModel comp01Topup(DepositTopupModel model) {
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
        ApiDepositModel apiCardDeposit = new ApiDepositModel();
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

    private ApiResultModel comp02Topup(DepositTopupModel model) {
        return new ApiResultModel(EnumService.APICompProviderEnum.COMP02, -1, MessageText.NotService);
    }

    private ApiResultModel comp03Topup(DepositTopupModel model) {
        return new ApiResultModel(EnumService.APICompProviderEnum.COMP03, -1, MessageText.NotService);
    }

}
