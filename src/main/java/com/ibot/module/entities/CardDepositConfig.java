/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.entities;

import com.ibot.module.base.entities.EntityModel;
import java.io.Serializable;
import java.util.Date;
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
@Table(name = "App_CardDepositConfig")
public class CardDepositConfig extends EntityModel implements Serializable {

    public CardDepositConfig() {
        this.id = UUID.randomUUID().toString().toLowerCase(); 
    }
    @Id
    @Column(name = "ID", length = 36, nullable = false)
    private String id;

    @Column(name = "GameID", length = 40, nullable = false)
    private String GameID;

    @Column(name = "GameCode", length = 20, nullable = false)
    private String GameCode;

    @Column(name = "PartnerID", length = 40, nullable = false)
    private String PartnerID;

    @Column(name = "PartnerCode", length = 20, nullable = false)
    private String PartnerCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameID() {
        return GameID;
    }

    public void setGameID(String GameID) {
        this.GameID = GameID;
    }

    public String getGameCode() {
        return GameCode;
    }

    public void setGameCode(String GameCode) {
        this.GameCode = GameCode;
    }

    public String getPartnerID() {
        return PartnerID;
    }

    public void setPartnerID(String PartnerID) {
        this.PartnerID = PartnerID;
    }

    public String getPartnerCode() {
        return PartnerCode;
    }

    public void setPartnerCode(String PartnerCode) {
        this.PartnerCode = PartnerCode;
    }

}
