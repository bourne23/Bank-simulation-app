package com.foxborn;

import com.foxborn.enums.AccountType;
import com.foxborn.model.Account;
import com.foxborn.model.Transaction;
import com.foxborn.service.AccountService;
import com.foxborn.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class BankSimulationApp {

    public static void main(String[] args) {
//Olessya>>
//        ApplicationContext context = SpringApplication.run(BankSimulationApp.class, args);
//
//        AccountService accountService = context.getBean(AccountService.class);
//
//        Account sender = accountService.createAccount(new BigDecimal(10000), new Date(01, 01, 2022), AccountType.CHECKING, 12L);
//        Account receiver = accountService.createAccount(new BigDecimal(20000), new Date(06, 06, 2021), AccountType.CHECKING, 28L);
//        List accounts = accountService.listAllAccounts();
//        System.out.println("Accounts = " + accounts);
//
//        TransactionService transactionService = context.getBean(TransactionService.class);
//        transactionService.makeTransfer(sender, receiver, new BigDecimal(5000), new Date(10, 10, 2022), "Fitness Class Purchase");
//        List <Transaction> list = transactionService.findAllTransactions();
//        System.out.println("Transactions  = " + list);
//
//        System.out.printf("", list.stream().filter(transaction -> transaction.getReceiver().equals(receiver.getUserId())).findAny());


        ApplicationContext container = SpringApplication.run(BankSimulationApp.class, args);

        //get account and transaction service beans
        AccountService accountService = container.getBean(AccountService.class);
        TransactionService transactionService = container.getBean(TransactionService.class);

        //create 2 account sender and receiver.
        Account sender = accountService.createAccount(BigDecimal.valueOf(70),new Date(),
                AccountType.CHECKING,1L);

        Account receiver = accountService.createAccount(BigDecimal.valueOf(50),new Date(),
                AccountType.CHECKING,2L);
        Account receiver2 = null;


        accountService.listAllAccounts().forEach(System.out::println);

        transactionService.makeTransfer(sender,receiver,new BigDecimal(40),new Date(),"Transaction 1");

        System.out.println(transactionService.findAllTransactions().get(0));

        accountService.listAllAccounts().forEach(System.out::println);

    }

}
