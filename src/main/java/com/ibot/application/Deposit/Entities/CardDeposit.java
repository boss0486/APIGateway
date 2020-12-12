/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.application.Deposit.Entities;

/**
 *
 * @author Allen
 */
 
public class CardDeposit  {
    private String id ;
    private String userName ;
    private String password ;
    private String pin ;
    private String seri ;
    private double cardValue ;
    private double cardReal ;
    private int code ;
    private String message ;
    private String logTransaction ;
    private String key ;
    private int cardtype ;

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    public double getCardValue() {
        return cardValue;
    }

    public void setCardValue(double cardValue) {
        this.cardValue = cardValue;
    }

    public double getCardReal() {
        return cardReal;
    }

    public void setCardReal(double cardReal) {
        this.cardReal = cardReal;
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

    public String getLogTransaction() {
        return logTransaction;
    }

    public void setLogTransaction(String logTransaction) {
        this.logTransaction = logTransaction;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCardtype() {
        return cardtype;
    }

    public void setCardtype(int cardtype) {
        this.cardtype = cardtype;
    }

}