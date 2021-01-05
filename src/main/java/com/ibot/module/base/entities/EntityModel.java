/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.base.entities;

import java.util.Date;
;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

/**
 *
 * @author Allen
 */


@MappedSuperclass // apply for all entity class
public class EntityModel {
    @Column(name = "LanguageID", length = 40, nullable = true)
    private String languageId;
    @Column(name = "Enabled")
    private int enabled;
    @Column(name = "CreatedBy", length = 40, nullable = true)
    private String createdBy;
    @Column(name = "CreatedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdDate;

    public EntityModel() {
        this.createdDate =  new  Date();
        this.createdBy = "api-logged";
        this.languageId = "en";
    }

    
    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }    
}
