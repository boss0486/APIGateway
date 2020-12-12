/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.application.Deposit.Services;

import com.ibot.application.Deposit.Entities.CardDeposit;
import com.ibot.application.Deposit.Entities.CardDepositCreate;
import com.ibot.notifization.JsonResult;
import com.ibot.notifization.Message;
import com.ibot.notifization.Notification;

/**
 *
 * @author Allen
 */
public class CardDepositService {

    public JsonResult Topup(CardDepositCreate model) {
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
            return Notification.Success(Message.Success);
        }
        return Notification.Error(Message.NotService);
    }

    private int ViettelProviderTopup(CardDepositCreate model) {
        //https://stackoverflow.com/questions/42365266/call-another-rest-api-from-my-server-in-spring-boot
        return 1;
    }

    private int MobiProviderTopup(CardDepositCreate model) {
        return 1;
    }

    private int VinaProviderTopup(CardDepositCreate model) {
        return 1;
    }

}
