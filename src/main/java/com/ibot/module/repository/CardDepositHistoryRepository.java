/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.repository;

import com.ibot.module.entities.CardDepositConfig;
import com.ibot.module.entities.CardDepositHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Allen
 */
public interface CardDepositHistoryRepository extends JpaRepository<CardDepositHistory, String>  {
    @Query(value = "SELECT * FROM App_CardDepositHistory WHERE UserID=:UserID", nativeQuery = true)
    public List<CardDepositConfig> findbyUserID(@Param("UserID") String userId);
}
