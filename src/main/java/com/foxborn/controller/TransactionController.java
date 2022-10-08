package com.foxborn.controller;

import com.foxborn.model.Account;
import com.foxborn.model.Transaction;
import com.foxborn.service.AccountService;
import com.foxborn.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Controller
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @GetMapping("/make-transfer")
    public String makeTransfer(Model model){

        //we need all accounts to provide them as sender, receiver
        model.addAttribute("accounts",accountService.listAllAccounts());
        //we need empty transaction object to get info from UI
        model.addAttribute("transaction", Transaction.builder().build());
        //we need list of last 10 transactions
        model.addAttribute("lastTransactions",transactionService.lastTransactionsList());

        return "transaction/make-transfer";
    }

    //TASK
    //write a post method, that takes transaction object from the method above,
    //complete the make transfer and return the same page.
    @PostMapping("/transfer")
    public String postMakeTransfer(@ModelAttribute("transaction") Transaction transaction,Model model){

        //I have UUID but I need to provide Account to make transfer method.
        Account sender = accountService.retrieveByID(transaction.getSender());
        Account receiver = accountService.retrieveByID(transaction.getReceiver());

        transactionService.makeTransfer(sender,receiver,transaction.getAmount(),new Date(),transaction.getMessage());

        return "redirect:/make-transfer";
    }

    /**
     * this method is for a button 'Account Transaction'
     */
    @GetMapping("/transaction/{id}")
    public String getTransaction(@PathVariable("id") UUID id, Model model){

        System.out.println("id = " + id);
        //get list of transactions based on id and return model attr

       model.addAttribute("transactions", transactionService.findTransactionListById(id));

        return "transaction/transactions";
    }
}
