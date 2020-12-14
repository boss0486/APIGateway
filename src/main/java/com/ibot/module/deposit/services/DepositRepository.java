/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.deposit.services;

import com.ibot.module.deposit.entities.DepositCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Allen
 */
@Repository
public interface DepositRepository extends CrudRepository<DepositCard, String> {

}
