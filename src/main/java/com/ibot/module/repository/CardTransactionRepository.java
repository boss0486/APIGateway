/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.repository;

import com.ibot.module.entities.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Allen
 */
public interface CardTransactionRepository extends JpaRepository<CardTransaction, String> {

    @Query(value = "SELECT TOP 1 * FROM App_CardTransaction WHERE CardSerial=:CardSerial", nativeQuery = true)
    public CardTransaction findbySerial(@Param("CardSerial") String cardSerial);

    //
    @Query(value = "SELECT TOP 1 * FROM App_CardTransaction WHERE (CardSerial = :CardSerial OR CardCode = :CardCode) AND CardType = :CardType", nativeQuery = true)
    public CardTransaction validCard(@Param("CardSerial") String cardSerial,@Param("CardCode") String cardCode, @Param("CardType") int cardType);

    //
    @Query(value = "SELECT TOP 1 * FROM App_CardTransaction WHERE CardCode=:CardCode", nativeQuery = true)
    public CardTransaction findbyCardCode(@Param("CardCode") String cardCode);

    //
    @Query(value = "SELECT TOP 1 * FROM App_CardTransaction WHERE CardCode = ?1 AND CardType = ?2", nativeQuery = true)
    public CardTransaction findbyCardCode(String cardCode, int cardType);
    //
}
