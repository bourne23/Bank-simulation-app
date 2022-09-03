package com.foxborn.service.impl;

import com.foxborn.enums.AccountType;
import com.foxborn.model.Account;
import com.foxborn.repository.AccountRepository;
import com.foxborn.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;   // need DB bean to save account to >> add @component

    public AccountServiceImpl(AccountRepository accountRepository) {  // create constructor to @autowire
        this.accountRepository = accountRepository;
    }

    //1. Create account - Save new account in DB, and return account
    @Override
    public Account createAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId) {

        Account account = Account.builder().id(UUID.randomUUID())
                .userId(userId).accountType(accountType)
                .balance(balance).creationDate(creationDate).build();

        //2. save and return as account
        return accountRepository.save(account);
    }

    //3. list all accounts in repo
    @Override
    public List<Account> listAllAccounts() {
        return accountRepository.findAll();
    }
}
