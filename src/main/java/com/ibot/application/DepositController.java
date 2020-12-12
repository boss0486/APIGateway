/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.application;

import com.ibot.application.Deposit.Entities.CardDeposit;
import com.ibot.application.Deposit.Entities.CardDepositCreate;
import com.ibot.application.Deposit.Services.CardDepositService;
import com.ibot.notifization.JsonResult;
import com.ibot.notifization.Message;
import com.ibot.notifization.Notification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Allen
 */
@RestController
public class DepositController {

    @ResponseBody
    @RequestMapping(value = "/api/test", method = RequestMethod.POST)
    public CardDeposit Test01(@RequestBody CardDeposit model) {
        return model;
    }

    //@PostMapping(value = "/api/deposit")
    @ResponseBody
    @RequestMapping(value = "/api/deposit", method = RequestMethod.POST)
    public JsonResult Deposit(@RequestBody CardDepositCreate model) {
        try {
            CardDepositService cardDepositService = new CardDepositService();
            return cardDepositService.Topup(model);

        } catch (Exception ex) {
            return Notification.Error("::" + ex);
        }

    }

}
