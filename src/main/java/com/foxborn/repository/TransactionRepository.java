package com.foxborn.repository;

import com.foxborn.exception.RecordNotFoundException;
import com.foxborn.model.Account;
import com.foxborn.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionRepository {

    List<Transaction> transactionList = new ArrayList<>();   // create transaction repository

    /**
     * Method that takes Account, adds it to the list (to the database), and then returns same account
     */
    public Transaction saveTransaction(Transaction transaction) {
        transactionList.add(transaction);
        System.out.println("transaction successful = " + transaction);
        return transaction;
    }
    /**
     * Method returns a list of all transactions from DB, to verify new transaction was added
     */
    public List<Transaction> findAllTransactions() {
        return transactionList;
    }
//    public Transaction findById(UUID sender, UUID receiver) {
//        return transactionList.stream().filter(transaction -> transaction.getSender().equals(sender))
//                .filter(transaction -> transaction.getReceiver().equals(receiver)).findAny()
//                .orElseThrow(() -> new RecordNotFoundException("Account does not exist in the DataBase"));
//    }
//

    /**
     *  write a stream that sort the transactions based on creation date and only return 10 of them
     */
    public List<Transaction> lastTransactions() {

        return transactionList.stream()
                .sorted(Comparator.comparing(Transaction::getCreationDate).reversed())
                .limit(10).collect(Collectors.toList());
    }


    public List<Transaction> findTransactionListById(UUID id) {

        return transactionList.stream()
                .filter(transaction -> transaction.getSender().equals(id)
                || transaction.getReceiver().equals(id))
                .collect(Collectors.toList());


    }

}
