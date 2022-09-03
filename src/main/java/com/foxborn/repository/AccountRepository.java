package com.foxborn.repository;

import com.foxborn.exception.BadRequestException;
import com.foxborn.exception.RecordNotFoundException;
import com.foxborn.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountRepository {

// List of accounts, use this instead of database, to temporarily hold
    public static List <Account> accountList = new ArrayList<>();

 //   Method that takes Account, adds it to the list (to the database), and then returns account
    public Account save (Account account){
        accountList.add(account);
        return account;
    }

    // Method returns all accounts from the list (repo)
    public List<Account> findAll() {
        return accountList;
    }

    /**
     *  Find account in the DB (repo list) , or throw exception if not found
     */

    public Account findById(UUID id) {
        return accountList.stream().filter(account -> account.getId().equals(id)).findAny()
                .orElseThrow(()-> new RecordNotFoundException("Account does not exist in the DataBase"));
    }



}