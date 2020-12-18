/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.impl;

import com.ibot.module.entities.CardDepositHistory;
import com.ibot.module.repository.CardDepositHistoryRepository;
import com.ibot.module.services.ICardHistoryService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Allen
 */
@Service
public class CardDepositHistoryImpl implements ICardHistoryService {

    @Autowired
    private CardDepositHistoryRepository cardDepositHistoryRepository;

    @Override
    public void loggedCardDepositHistory(String title, String summary, String transactionId, String userId, double amount) {

        CardDepositHistory cardDepositHistory = new CardDepositHistory();
        cardDepositHistory.setTitle(title);
        cardDepositHistory.setSummary(summary);
        cardDepositHistory.setTransactionID(transactionId);
        cardDepositHistory.setUserID(userId);
        cardDepositHistory.setAmount(amount);
        cardDepositHistory.setCreatedDate(new Date());
        cardDepositHistoryRepository.save(cardDepositHistory);
    }
}
