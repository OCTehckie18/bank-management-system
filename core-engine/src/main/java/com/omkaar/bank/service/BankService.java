package com.omkaar.bank.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import com.omkaar.bank.model.Account;
import com.omkaar.bank.model.LoanRequest;
import com.omkaar.bank.model.Transaction;
import com.omkaar.bank.model.TransactionType;

public class BankService implements BankOperations {

    private final Map<String, Account> accounts = new HashMap<>();
    private final Stack<Transaction> transactionStack = new Stack<>();
    private final Queue<LoanRequest> loanQueue = new LinkedList<>();
    private final TransactionHistory transactionHistory = new InMemoryTransactionHistory();

    @Override
    public void registerAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    // @Override
    // public void registerAccount(String accountId) {
    // throw new UnsupportedOperationException(
    // "Account registration must be done via domain creation");
    // }

    @Override
    public void deposit(String accountId, BigDecimal amount) {
        Account account = getAccount(accountId);

        account.deposit(amount);

        Transaction tx = new Transaction(
                TransactionType.DEPOSIT,
                null,
                accountId,
                amount);

        transactionStack.push(tx);
        transactionHistory.record(tx);

    }

    @Override
    public void withdraw(String accountId, BigDecimal amount) {
        Account account = getAccount(accountId);

        account.withdraw(amount);

        Transaction tx = new Transaction(
                TransactionType.WITHDRAWAL,
                accountId,
                null,
                amount);

        transactionStack.push(tx);
        transactionHistory.record(tx);

    }

    @Override
    public void transfer(String fromId, String toId, BigDecimal amount) {
        Account from = getAccount(fromId);
        Account to = getAccount(toId);

        from.withdraw(amount);
        to.deposit(amount);

        Transaction tx = new Transaction(
                TransactionType.TRANSFER,
                fromId,
                toId,
                amount);

        transactionStack.push(tx);
        transactionHistory.record(tx);

    }

    @Override
    public void undoLastTransaction() {
        if (transactionStack.isEmpty()) {
            throw new IllegalStateException("No transactions to undo");
        }

        Transaction tx = transactionStack.pop();

        switch (tx.getType()) {
            case DEPOSIT -> {
                Account acc = getAccount(tx.getToAccountId());
                acc.withdraw(tx.getAmount());
            }
            case WITHDRAWAL -> {
                Account acc = getAccount(tx.getFromAccountId());
                acc.deposit(tx.getAmount());
            }
            case TRANSFER -> {
                Account from = getAccount(tx.getFromAccountId());
                Account to = getAccount(tx.getToAccountId());

                to.withdraw(tx.getAmount());
                from.deposit(tx.getAmount());
            }
        }
    }

    private Account getAccount(String accountId) {
        Account acc = accounts.get(accountId);
        if (acc == null) {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }
        return acc;
    }

    @Override
    public void requestLoan(String accountId, BigDecimal amount) {
        getAccount(accountId); // validate existence

        LoanRequest request = new LoanRequest(accountId, amount);
        loanQueue.offer(request);
    }

    @Override
    public LoanRequest processNextLoanRequest() {
        LoanRequest request = loanQueue.poll();

        if (request == null) {
            throw new IllegalStateException("No loan requests to process");
        }

        Account account = getAccount(request.getAccountId());
        account.deposit(request.getAmount());

        Transaction tx = new Transaction(
                TransactionType.DEPOSIT,
                null,
                request.getAccountId(),
                request.getAmount());

        transactionStack.push(tx); // optional, but consistent
        transactionHistory.record(tx); // CRITICAL LINE

        return request;
    }

    @Override
    public List<Transaction> getTransactionHistory(String accountId) {
        return transactionHistory.getByAccountId(accountId);
    }

}
