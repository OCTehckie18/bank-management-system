package com.omkaar.bank.service;

import java.math.BigDecimal;

import com.omkaar.bank.model.LoanRequest;

public interface BankOperations {

    void registerAccount(String accountId);

    void deposit(String accountId, BigDecimal amount);

    void withdraw(String accountId, BigDecimal amount);

    void transfer(String fromAccountId, String toAccountId, BigDecimal amount);

    void undoLastTransaction();

    void requestLoan(String accountId, BigDecimal amount);

    LoanRequest processNextLoanRequest();
}
