package com.omkaar.bank.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omkaar.bank.model.LoanRequest;
import com.omkaar.bank.service.BankOperations;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final BankOperations bank;

    public LoanController(BankOperations bank) {
        this.bank = bank;
    }

    @PostMapping("/request")
    public void requestLoan(
            @RequestParam String accountId,
            @RequestParam BigDecimal amount) {
        bank.requestLoan(accountId, amount);
    }

    @PostMapping("/process")
    public LoanRequest processLoan() {
        return bank.processNextLoanRequest();
    }
}
