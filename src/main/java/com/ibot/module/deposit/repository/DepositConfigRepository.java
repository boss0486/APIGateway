/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.deposit.repository;

import com.ibot.module.deposit.entities.DepositConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Allen
 */
public interface DepositConfigRepository extends JpaRepository<DepositConfig, String> {

    @Query(value = "SELECT TOP 1 * FROM App_DepositConfig WHERE GameCode=:GameCode", nativeQuery = true)
    public DepositConfig findbyGameCode(@Param("GameCode") String gameCode);

    //
    @Query(value = "SELECT TOP 1 * FROM App_DepositConfig WHERE GameCode=:GameCode AND CompProviderID=:CompProviderID", nativeQuery = true)
    public DepositConfig findbyGameCode(@Param("GameCode") String gameCode, @Param("CompProviderID") int compProviderID);
}
