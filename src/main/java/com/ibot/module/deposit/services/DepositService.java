/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.deposit.services;

import com.google.gson.Gson;
import com.ibot.library.Security;
import com.ibot.library.ValidData;
import com.ibot.module.deposit.entities.ApiDepositModel;
import com.ibot.module.deposit.entities.DepositCard;
import com.ibot.module.deposit.entities.DepositTopupModel;
import com.ibot.module.type.IntProviderOption;
import com.ibot.module.type.InternetProvider;
import com.ibot.notifization.JsonResult;
import com.ibot.notifization.Message;
import com.ibot.notifization.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Allen
 */

public interface DepositService {
JsonResult Topup(DepositTopupModel model);
    
}
