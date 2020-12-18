/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.base.entities;

import com.ibot.module.type.EnumService.APIPartnerEnum;

/**
 *
 * @author Allen
 */
public class ApiResultModel {

    private APIPartnerEnum compEnum;
    private int code;
    private String message;

    public APIPartnerEnum getCompEnum() {
        return compEnum;
    }

    public void setCompEnum(APIPartnerEnum compEnum) {
        this.compEnum = compEnum;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiResultModel(APIPartnerEnum compEnum, int code, String message) {
        this.compEnum = compEnum;
        this.code = code;
        this.message = message;
    }

}
