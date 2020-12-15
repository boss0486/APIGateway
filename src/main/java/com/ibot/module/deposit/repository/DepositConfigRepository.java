/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.deposit.repository;

import com.ibot.module.deposit.entities.DepositConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Allen
 */
public interface DepositConfigRepository {

    @Query(value = "SELECT TOP 1 * FROM App_DepositConfig WHERE ID=:ID", nativeQuery = true)
    DepositConfig findbyID(@Param("ID") String id);
    
    @Query(value = "SELECT TOP 1 * FROM App_DepositConfig WHERE GameCode=:GameCode", nativeQuery = true)
    DepositConfig findbyGameCode(@Param("GameCode") String gameCode);
    

}
