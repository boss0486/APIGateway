/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.notifization;

import org.springframework.http.HttpStatus;

/**
 *
 * @author Allen
 */
public class InternalNotifization {

    public JsonDataResult DataResult(String message, Object data) {
        return new JsonDataResult(HttpStatus.OK.value(), message, data);
    }

    public JsonResult SuccessResult(String message) {
        return new JsonResult(HttpStatus.OK.value(), message);
    }

    public JsonDataResult SuccessResult(String message, Object data) {
        return new JsonDataResult(HttpStatus.OK.value(), message, data);
    }

    public JsonResult InvalidResult(String message) {
        return new JsonResult(HttpStatus.BAD_REQUEST.value(), message);
    }

    public JsonResult InvalidResult(HttpStatus status, String message) {
        return new JsonResult(status.value(), message);
    }

    public JsonResult NotFoundResult(String message) {
        return new JsonResult(HttpStatus.NOT_FOUND.value(), message);
    }

    public JsonResult ErrorResult(String message) {
        return new JsonResult(HttpStatus.SERVICE_UNAVAILABLE.value(), message);
    }

    public JsonResult ErrorResult(HttpStatus status, String message) {
        return new JsonResult(status.value(), message);
    }

}
