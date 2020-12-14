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

    @Column(name = "created_by", length = 36, nullable = true)
    private String created_by;
    @Column(name = "created_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created_date;
    @Column(name = "modified_by", length = 36, nullable = true)
    private String modified_by;
    @Column(name = "modified_date")
    private String modified_date;
}
