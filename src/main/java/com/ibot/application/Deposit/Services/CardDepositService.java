/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.application.Deposit.Services;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.ibot.application.Deposit.Entities.ApiCardDepositModel;
import com.ibot.application.Deposit.Entities.CardDepositTopupModel;
import com.ibot.notifization.JsonResult;
import com.ibot.notifization.Message;
import com.ibot.notifization.Notification;

/**
 *
 * @author Allen
 */
public class CardDepositService {

    public JsonResult Topup(CardDepositTopupModel model) {
        // something here
        int cardType = model.cardType;
        double cardValue = model.cardValue;
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
            return Notification.Success(Message.Success +  "2");
        }
        return Notification.Error(Message.NotService);
    }

    private int ViettelProviderTopup(CardDepositTopupModel model) {
        // convert param obj to json
        ApiCardDepositModel apiCardDeposit = new ApiCardDepositModel();
        apiCardDeposit.id = "1";
        apiCardDeposit.userName = "2";
        apiCardDeposit.password = "3";
        apiCardDeposit.pin = "233";
        apiCardDeposit.seri = "4324";
        apiCardDeposit.cardValue = 0;
        apiCardDeposit.cardReal = 0;
        apiCardDeposit.code = 0;
        apiCardDeposit.message = "43";
        apiCardDeposit.logTransaction = "432";
        apiCardDeposit.key = "ACZ";
        apiCardDeposit.cardtype = 0;
        // strin param
        Gson gson = new Gson();
        String jsonParam = gson.toJson(apiCardDeposit);
        
        System.out.print("Rs:" + jsonParam);  
        return 1;
    }

    private int MobiProviderTopup(CardDepositTopupModel model) {
        return 1;
    }

    private int VinaProviderTopup(CardDepositTopupModel model) {
        return 1;
    }

}
