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
public class JsonResult {

    private int status;
    private String message;
    private Object data;

    public void setStatus(int _status) {
        this.status = _status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setMessage(String _message) {
        this.message = _message;
    }

    public String getMessage() {
        return this.message;
    }

    public JsonResult(int _status, String _message) {
        this.status = _status;
        this.message = _message;
    }
    
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public JsonResult(int _status, String _message, Object _data) {
        this.status = _status;
        this.message = _message;
        this.data = _data;
    }
}
