/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.deposit.services.Impl;

import com.google.gson.Gson;
import com.ibot.library.Security;
import com.ibot.library.ValidData;
import com.ibot.module.deposit.entities.ApiDepositModel;
import com.ibot.module.deposit.entities.DepositCard;
import com.ibot.module.deposit.entities.DepositTopupModel;
import com.ibot.module.deposit.repository.DepositRepository;
import com.ibot.module.deposit.services.DepositService;
import com.ibot.module.type.IntProviderOption;
import com.ibot.module.type.InternetProvider;
import com.ibot.notifization.JsonResult;
import com.ibot.notifization.Message;
import com.ibot.notifization.Notification;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Allen
 */
@Service
public class DepositServiceImpl implements DepositService{
    @Autowired
    private DepositRepository depositRepository;

    @Override
    public JsonResult Topup(DepositTopupModel model) {
//        DepositRepository depositRepository=new DepositRepository
        List<DepositCard> xx=depositRepository.findAll();
        System.out.println("------->"+ xx.size());
        //
        if (model == null) {
            return Notification.Invalid(Message.Invalid);
        }
        //  model
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
        InternetProvider internetProvider = new InternetProvider();
        IntProviderOption intProviderOption = internetProvider.InternetProviderData.stream().filter(m -> m.id == cardType).findAny().orElse(null);
        //
        if (intProviderOption == null) {
            return Notification.Invalid(Message.Invalid);
        }
        //
        int result = 0;
        switch (cardType) {
            case 1: // VIETTEL
                result = ViettelProviderTopup(model);
                break;
            case 2: // MOBI PHONE
                result = MobiProviderTopup(model);
                break;
            case 3: // VINA PHONE
                result = ViettelProviderTopup(model);
                break;
            default:
                break;
        }

        if (result == 1) {
            return Notification.Success(Message.Success + "2");
        }
        return Notification.Error(Message.NotService);
    }

    private int ViettelProviderTopup(DepositTopupModel model) {
        String loginId = model.loginId;
        int cardType = model.cardType;
        int cardValue = model.cardValue;
        String cardCode = model.cardCode;
        String cardSerial = model.cardSerial;

        String partnerName = "T-PartName01";
        String password = "T-Password";
        String secretkey = "T-Secretkey";

        InternetProvider internetProvider = new InternetProvider();
        IntProviderOption intProviderOption = internetProvider.InternetProviderData.stream().filter(m -> m.id == cardType).findAny().orElse(null);
        //
        String signatureHash = Security.getMd5(intProviderOption.code + cardCode + cardSerial + partnerName + password + secretkey);

        //String  signature =  "dsad";  
        //String md5Hex = DigestUtils.md5Hex(signature).toUpperCase();
        ApiDepositModel apiCardDeposit = new ApiDepositModel();
        apiCardDeposit.partnerName = partnerName;
        apiCardDeposit.password = password;
        apiCardDeposit.secretkey = secretkey;
        apiCardDeposit.cardtype = intProviderOption.code; // VT or MOBILE or VINA
        apiCardDeposit.cardValue = cardValue;
        apiCardDeposit.cardSerial = cardSerial;
        apiCardDeposit.cardCode = cardCode;
        apiCardDeposit.signature = signatureHash;
        // strin param
        Gson gson = new Gson();
        String jsonParam = gson.toJson(apiCardDeposit);
        // call api service
        int transactionStatus = 1;
        // save data 
        DepositCard deposit = new DepositCard();
        deposit.setLoginId(loginId);
        deposit.setCardType(cardType);
        deposit.setCardValue(cardValue);
        deposit.setCardCode(cardCode);
        deposit.setCardSerial(cardSerial);
        deposit.setTransactionStatus(transactionStatus);

        DepositCard depositRs = this.depositRepository.save(deposit);

        System.out.print("Rs:" + depositRs);

        return 1;
    }

    private int MobiProviderTopup(DepositTopupModel model) {
        return 1;
    }

    private int VinaProviderTopup(DepositTopupModel model) {
        return 1;
    }

    public DepositCard saveCard(DepositCard depositCard) {

        DepositCard depositCard1 = depositRepository.save(depositCard);
        return depositCard1;
    }
}
