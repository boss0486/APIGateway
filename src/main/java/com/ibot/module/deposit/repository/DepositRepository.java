/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.deposit.repository;

import com.ibot.module.deposit.entities.DepositCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Allen
 */
public interface DepositRepository extends JpaRepository<DepositCard, String> {

    @Query(value = "SELECT TOP 1 * FROM app_deposit WHERE CardSerial=:CardSerial", nativeQuery = true)
    DepositCard findbySerial(@Param("CardSerial") String cardSerial);

    //
    @Query(value = "SELECT TOP 1 * FROM app_deposit WHERE CardSerial = ?1 AND CardType = ?2", nativeQuery = true)
    DepositCard findbySerial(String cardSerial, int cardType);

    //
    @Query(value = "SELECT TOP 1 * FROM app_deposit WHERE CardCode=:CardCode", nativeQuery = true)
    DepositCard findbyCardCode(@Param("CardCode") String cardCode);

    //
    @Query(value = "SELECT TOP 1 * FROM app_deposit WHERE CardCode = ?1 AND CardType = ?2", nativeQuery = true)
    DepositCard findbyCardCode(String cardCode, int cardType);
    //
}
 
