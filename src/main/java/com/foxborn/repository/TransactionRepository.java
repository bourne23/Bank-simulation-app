package com.foxborn.repository;

import com.foxborn.exception.RecordNotFoundException;
import com.foxborn.model.Account;
import com.foxborn.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionRepository {

    List<Transaction> transactionList = new ArrayList<>();   // create transaction repository

    public Transaction findById(UUID sender, UUID receiver) {
       return transactionList.stream().filter(transaction -> transaction.getSender().equals(sender))
               .filter(transaction -> transaction.getReceiver().equals(receiver)).findAny()
                       .orElseThrow(()-> new RecordNotFoundException("Account does not exist in the DataBase"));
    }

    /**
     * Method that takes Account, adds it to the list (to the database), and then returns same account
     */
    public Transaction saveTransaction (Transaction transaction){
        transactionList.add(transaction);
        System.out.println("transaction successful = " + transaction);
        return transaction;
    }


    /**
     *  Method returns a list of all transactions from DB, to verify new transaction was added
     */
    public List<Transaction> findAllTransactions() {
        return transactionList;
    }




}
