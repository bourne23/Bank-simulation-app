package com.foxborn.service.impl;

import com.foxborn.enums.AccountType;
import com.foxborn.exception.AccountOwnershipException;
import com.foxborn.exception.BadRequestException;
import com.foxborn.exception.BalanceNotSufficientException;
import com.foxborn.model.Account;
import com.foxborn.model.Transaction;
import com.foxborn.repository.AccountRepository;
import com.foxborn.service.TransactionService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionServiceImpl implements TransactionService {
    AccountRepository accountRepository;  // Injecting dependency - add @Component and constructor to automatically @autowire

    public TransactionServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Method makeTransfer()
     * To make transfer :
     * - 1. validate sender and receiver exists, is it null, are they same id's, dont exist in DB>
     * -2. Check ownership of accounts, is two users or one user making transfer
     * -3. Execute transfer
     */
    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {
        validateAccount(sender, receiver);
        checkAccountOwnership(sender, receiver);
        executeBalanceAndUpdateIfRequired(amount, sender, receiver); // if the amount that sender is sending
        return null;
    }


    /**
     * 1 . Validate account : 1. not null, 2. same user or not, 3. accounts exist in db?
     */
    private void validateAccount(Account sender, Account receiver) {   // throw exception if not valid
        // 1. -Validate that accounts are not null,
        if (sender == null || receiver == null) {
            throw new BadRequestException("Sender and Receiver cannot be null");
        }
        // 2. -or account ids are the same (checking to checking of same user cant be same ?),
        if (sender.getId().equals(receiver.getId())) {
            throw new BadRequestException("Sender and Receiver accounts cannot be the same.");
        }
        // 3. do accounts exist in the DB / repo list?
        findAccountByID(sender.getId());
        findAccountByID(receiver.getId());
    }

    /**
     * 2. Check if both are Checking accounts, and two users transferring money.
     * If one is Savings, then check if both accounts belong to the same user, making transfer between their own accounts.
     *
     * @param sender
     * @param receiver
     */
    private void checkAccountOwnership(Account sender, Account receiver) {
        if ((sender.getAccountType().equals(AccountType.SAVINGS) || receiver.getAccountType().equals(AccountType.SAVINGS))  // if sender or receiver account is Savings
                && !sender.getUserId().equals(receiver.getUserId())) {                                               // and sender and receiver accounts are NOT the same User then throw Exception
            throw new AccountOwnershipException("One of the accounts is Savings. Transactions between savings and checking account are allowed between same user accounts only. User Ids dont match.");
        }
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, Account sender, Account receiver) {
        if (checkSenderBalance(sender, amount)) {
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        } else {
            throw new BalanceNotSufficientException("Not enough balance in the account");
        }

    }

    // Verify that sender has enough balance
    private boolean checkSenderBalance(Account sender, BigDecimal amount) {
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >= 0;

    }

    /**
     * Find account by ID in the DB. Injected dependency AccountRepository
     *
     * @param id
     * @return
     */
    private Account findAccountByID(UUID id) {
        return accountRepository.findById(id);

    }

    @Override
    public List<Transaction> findAllTransactions() {
        return null;
    }
}
