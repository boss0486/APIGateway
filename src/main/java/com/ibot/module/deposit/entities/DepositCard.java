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
@Table(name = "App_Deposit")
public class DepositCard extends EntityModel implements Serializable {

    public DepositCard() {
        this.id = UUID.randomUUID().toString().toLowerCase();
    }
    @Id
    @Column(name = "ID", length = 36, nullable = false)
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    @Column(name = "CompProviderID", nullable = false)
    private int CompProviderID;
    @Column(name = "LoginID", length = 36, nullable = false)
    private String loginId;
    @Column(name = "CardType")
    private int cardType;
    @Column(name = "CardValue")
    private int cardValue;
    @Column(name = "CardSerial", length = 20, nullable = false)
    private String cardSerial;
    @Column(name = "CardCode", length = 20, nullable = false)
    private String cardCode;
    @Column(name = "TransactionStatus")
    private int transactionStatus;

    // **************************************************************************
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public int getCardValue() {
        return cardValue;
    }

    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    public String getCardSerial() {
        return cardSerial;
    }

    public void setCardSerial(String cardSerial) {
        this.cardSerial = cardSerial;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public int getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(int transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public int getCompProviderID() {
        return CompProviderID;
    }

    public void setCompProviderID(int compProviderId) {
        this.CompProviderID = compProviderId;
    }
}
