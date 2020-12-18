/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.entities;

import com.ibot.module.base.entities.EntityModel;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Allen
 */
@Entity
@Table(name = "App_CardDepositHistory")
public class CardDepositHistory extends EntityModel implements Serializable {

    public CardDepositHistory() {
        this.id = UUID.randomUUID().toString().toLowerCase();
    }
    @Id
    @Column(name = "ID", length = 40, nullable = false)
    private String id;
    @Column(name = "Title", length = 255, nullable = false)
    private String title; 
    @Column(name = "Summary", length = 255, nullable = false)
    private String summary;
    @Column(name = "TransactionID", length = 40, nullable = false)
    private String transactionId;
    @Column(name = "UserID", length = 40, nullable = false)
    private String userId;
    @Column(name = "Amount", nullable = false)
    private double amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

 

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTransactionID() {
        return transactionId;
    }

    public void setTransactionID(String transactionID) {
        this.transactionId = transactionID;
    }

    public String getUserID() {
        return userId;
    }

    public void setUserID(String userID) {
        this.userId = userID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
