/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.repository;

import com.ibot.module.entities.CardDepositConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Allen
 */
public interface CardDepositConfigRepository extends JpaRepository<CardDepositConfig, String> {

    @Query(value = "SELECT TOP 1 * FROM App_CardDepositConfig WHERE GameCode=:GameCode", nativeQuery = true)
    public CardDepositConfig findbyGameCode(@Param("GameCode") String gameCode);

    //
    @Query(value = "SELECT TOP 1 * FROM App_CardDepositConfig WHERE GameCode=:GameCode AND PartnerCode=:PartnerCode", nativeQuery = true)
    public CardDepositConfig findbyGameCode(@Param("GameCode") String gameCode, @Param("PartnerCode") String partnerCode);
}
