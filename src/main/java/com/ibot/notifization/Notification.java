/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.notifization;
/**
 *
 * @author Allen
 */
public class Notification {

    public static JsonResult Data(String message, Object data) {
        InternalNotifization internalNotifization = new InternalNotifization();
        return internalNotifization.DataResult(message, data);
    }

    public static JsonResult Success(String message) {
        InternalNotifization internalNotifization = new InternalNotifization();
        return internalNotifization.SuccessResult(message);
    }

    public static JsonResult Success(String message, Object data) {
        InternalNotifization internalNotifization = new InternalNotifization();
        return internalNotifization.SuccessResult(message, data);
    }

    public static JsonResult Invalid(String message) {
        InternalNotifization internalNotifization = new InternalNotifization();
        return internalNotifization.InvalidResult(message);
    }

    public static JsonResult Error(String message) {
        InternalNotifization internalNotifization = new InternalNotifization();
        return internalNotifization.ErrorResult(message);
    }
}
