/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.deposit.entities;

import com.ibot.module.base.entities.EntityModel;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 * @author Allen
 */
@Entity
@Table(name = "app_deposit")
public class DepositCard implements Serializable {

    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String id = UUID.randomUUID().toString().toLowerCase();
    //
    @Column(name = "loginid", length = 36, nullable = false)
    private String loginid;
    @Column(name = "card_type")
    private int card_type;
    @Column(name = "card_value")
    private int card_value;
    @Column(name = "card_serial", length = 20, nullable = false)
    private String card_serial;
    @Column(name = "card_code", length = 20, nullable = false)
    private String card_code;
    @Column(name = "transaction_status")
    private int transaction_status;

    // **************************************************************************
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginid;
    }

    public void setLoginId(String loginId) {
        this.loginid = loginId;
    }

    public int getCardType() {
        return card_type;
    }

    public void setCardType(int cardType) {
        this.card_type = cardType;
    }

    public int getCardValue() {
        return card_value;
    }

    public void setCardValue(int cardValue) {
        this.card_value = cardValue;
    }

    public String getCardSerial() {
        return card_serial;
    }

    public void setCardSerial(String cardSerial) {
        this.card_serial = cardSerial;
    }

    public String getCardCode() {
        return card_code;
    }

    public void setCardCode(String cardCode) {
        this.card_code = cardCode;
    }

    public int getTransactionStatus() {
        return transaction_status;
    }

    public void setTransactionStatus(int transactionStatus) {
        this.transaction_status = transactionStatus;
    }
}
