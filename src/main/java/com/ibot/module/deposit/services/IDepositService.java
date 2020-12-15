/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.deposit.services;

import com.ibot.module.deposit.entities.DepositTopupModel;
import com.ibot.notifization.JsonResult;

/**
 *
 * @author Allen
 */
public interface IDepositService {
    JsonResult Topup(DepositTopupModel model);
}
