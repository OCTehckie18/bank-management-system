package com.omkaar.bank.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.*;

import com.omkaar.bank.model.Account;
import com.omkaar.bank.model.AccountType;
import com.omkaar.bank.service.AccountFactory;
import com.omkaar.bank.service.BankOperations;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final BankOperations bank;

    public AccountController(BankOperations bank) {
        this.bank = bank;
    }

    @PostMapping("/create")
    public String createAccount(
            @RequestParam AccountType type,
            @RequestParam BigDecimal initialBalance) {
        Account account = AccountFactory.createAccount(type, initialBalance);
        bank.registerAccount(account);
        return account.getAccountId();
    }

    @PostMapping("/{id}/deposit")
    public void deposit(
            @PathVariable String id,
            @RequestParam BigDecimal amount) {
        bank.deposit(id, amount);
    }

    @PostMapping("/{id}/withdraw")
    public void withdraw(
            @PathVariable String id,
            @RequestParam BigDecimal amount) {
        bank.withdraw(id, amount);
    }
}
