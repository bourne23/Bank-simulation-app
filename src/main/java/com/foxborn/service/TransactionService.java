package com.foxborn.service;

import com.foxborn.model.Account;
import com.foxborn.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TransactionService {

    /**
     *  Method which processes transfer
     * @param sender
     * @param receiver
     * @param amount
     * @param creationDate
     * @param message
     * @return
     */
    Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message);

    /**
     * Method which returns a list of transactions between sender and receiver
     * @return
     */
    List <Transaction> findAllTransactions();

    List<Transaction> lastTransactionsList();

    List<Transaction> findTransactionListById(UUID id);
}
