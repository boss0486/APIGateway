/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.entities;

/**
 *
 * @author Allen
 */
public class Api01TopupResult { 
    public int status ;
    public String message;
    public String transID;

    public Api01TopupResult(int status, String message, String transID) {
        this.status = status;
        this.message = message;
        this.transID = transID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransID() {
        return transID;
    }

    public void setTransID(String transID) {
        this.transID = transID;
    }
    
}
 