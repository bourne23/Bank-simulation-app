package com.foxborn.repository;
import com.foxborn.exception.RecordNotFoundException;
import com.foxborn.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This Class represents DataBase.
 * - Saves accounts.
 * - Returns list of all accounts.
 */
@Component
public class AccountRepository {
    public static List <Account> accountList = new ArrayList<>(); // create List of all accounts

    /**
     * Method that takes Account, adds it to the list (to the database), and then returns same account
      */
    public Account save (Account account){
        accountList.add(account);
        return account;
    }

    /**
     *  Method returns a list of all accounts from DB
      */
    public List<Account> findAll() {
        return accountList;
    }

    /**
     *  Find account in the DB by UUID and return it, otherwise throw exception if not found.
     */
    public Account findById(UUID id) {
        return accountList.stream().filter(account -> account.getId().equals(id)).findAny()
                .orElseThrow(()-> new RecordNotFoundException("Account does not exist in the DataBase"));
    }



}