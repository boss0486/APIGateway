/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.application.controller;

import com.ibot.module.deposit.entities.ApiDepositModel;
import com.ibot.module.deposit.entities.DepositTopupModel;
import com.ibot.module.deposit.services.DepositService;
import com.ibot.module.deposit.services.Impl.DepositServiceImpl;
import com.ibot.notifization.JsonResult;
import com.ibot.notifization.Notification;
import org.springframework.beans.factory.annotation.Autowired;
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
//@RequestMapping("/api")
public class DepositController {

    @Autowired
    DepositService cardDepositService;

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ApiDepositModel Test01(@RequestBody ApiDepositModel model) {
        return model;
    }

    //@PostMapping(value = "/api/deposit")
    @ResponseBody
    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public JsonResult Deposit(@RequestBody DepositTopupModel model) {
//        DepositService cardDepositService=new DepositServiceImpl();
        return cardDepositService.Topup(model);

    }

}
