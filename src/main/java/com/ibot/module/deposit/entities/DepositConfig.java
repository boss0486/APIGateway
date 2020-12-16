/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.deposit.entities;

import com.ibot.module.base.entities.EntityModel;
import java.io.Serializable;
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
@Table(name = "App_DepositConfig")
public class DepositConfig extends EntityModel implements Serializable {

    public DepositConfig() {
        this.id = UUID.randomUUID().toString().toLowerCase();
    }
    @Id
    @Column(name = "ID", length = 36, nullable = false)
    private String id;

    @Column(name = "GameCode", length = 20, nullable = false)
    private String GameCode;

    @Column(name = "CompProviderID", nullable = false)
    private int CompProviderID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameCode() {
        return GameCode;
    }

    public void setGameCode(String GameCode) {
        this.GameCode = GameCode;
    }

    public int getCompProviderID() {
        return CompProviderID;
    }

    public void setCompProviderID(int CompProviderID) {
        this.CompProviderID = CompProviderID;
    }
}
