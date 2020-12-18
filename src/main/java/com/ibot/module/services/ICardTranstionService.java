/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.services;

import com.ibot.module.entities.TopupModel;
import com.ibot.notifization.JsonResult;

/**
 *
 * @author Allen
 */
public interface ICardTranstionService {

    JsonResult Topup(TopupModel model);
}
