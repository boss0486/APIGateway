/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.application.controller;

import com.ibot.datawsdl.soapxml.ApiRechargeService;
import com.ibot.module.entities.TopupModel;
import com.ibot.notifization.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ibot.module.services.ICardTranstionService; 
import com.ibot.notifization.JsonDataResult;
import com.ibot.notifization.Notification;

/**
 *
 * @author Allen
 */
@RestController
//@RequestMapping("/api")
public class DepositController {

    @Autowired
    ICardTranstionService iCardTranstionService;

    @ResponseBody
    @RequestMapping(value = "api/test", method = RequestMethod.GET)
    public JsonDataResult Test01() {
         ApiRechargeService apiRechargeService = new ApiRechargeService(); 
       return Notification.Data("OK", apiRechargeService.apiTest());
    }

    //@PostMapping(value = "/api/deposit")
    @ResponseBody
    @RequestMapping(value = "api/transaction/topup", method = RequestMethod.POST)
    public JsonResult ApiTopup(@RequestBody TopupModel model) {
       return iCardTranstionService.Topup(model);  
    }
}
