/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.services;
/**
 *
 * @author Allen
 */
public interface ICardHistoryService {
    void loggedCardDepositHistory(String title, String summary, String transactionId, String userId, double amount);
}
