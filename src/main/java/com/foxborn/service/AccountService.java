package com.foxborn.service;

import com.foxborn.enums.AccountType;
import com.foxborn.model.Account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account createAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId );

    List<Account> listAllAccounts();

    void deleteAccount(UUID id);

    Account retrieveByID(UUID uuid);
}

