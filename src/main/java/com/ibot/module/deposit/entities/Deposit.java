/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.deposit.entities;

import com.ibot.module.base.entities.EntityModel;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 * @author Allen
 */
@Entity
@Table(name = "Deposit")
public class Deposit extends EntityModel implements Serializable {
    @Id
    @Column
    private String Title;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
}
