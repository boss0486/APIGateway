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
    private String modifiedDate;
}
