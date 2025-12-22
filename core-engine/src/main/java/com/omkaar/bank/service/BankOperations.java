package com.omkaar.bank.service;

import java.math.BigDecimal;
import java.util.List;

import com.omkaar.bank.model.Account;
import com.omkaar.bank.model.LoanRequest;
import com.omkaar.bank.model.Transaction;

public interface BankOperations {

    void registerAccount(Account account);

    void deposit(String accountId, BigDecimal amount);

    void withdraw(String accountId, BigDecimal amount);

    void transfer(String fromAccountId, String toAccountId, BigDecimal amount);

    void undoLastTransaction();

    void requestLoan(String accountId, BigDecimal amount);

    LoanRequest processNextLoanRequest();

    List<Transaction> getTransactionHistory(String accountId);

}
