/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.deposit.entities;

import com.ibot.module.base.entities.EntityModel;
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
public class DepositConfig extends EntityModel {

    public DepositConfig() {
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
    @Column(name = "GameCode", length = 20, nullable = false)
    private String GameCode;

    @Column(name = "CompProviderID")
    private int CompProviderID;

}
