/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.deposit.repository;

import com.ibot.module.deposit.entities.DepositCard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Allen
 */
public interface DepositRepository extends JpaRepository<DepositCard, String> {
//    DepositCard findOneByCode(String id);
}

 