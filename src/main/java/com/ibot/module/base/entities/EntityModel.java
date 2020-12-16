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

    @Column(name = "CreatedBy", length = 36, nullable = true)
    private String createdBy;
    @Column(name = "CreatedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdDate;
    @Column(name = "ModifiedBy", length = 36, nullable = true)
    private String modifiedBy;
    @Column(name = "ModifiedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date modifiedDate;

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

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
}
