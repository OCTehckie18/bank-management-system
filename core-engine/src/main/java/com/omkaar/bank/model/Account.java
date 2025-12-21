package com.omkaar.bank.model;

import java.math.BigDecimal;
import java.util.UUID;

import com.omkaar.bank.exception.AccountFrozenException;
import com.omkaar.bank.exception.InsufficientBalanceException;

public abstract class Account {

    protected final String accountId;
    protected BigDecimal balance;
    protected boolean frozen;

    protected Account(BigDecimal initialBalance) {
        this.accountId = UUID.randomUUID().toString();
        this.balance = initialBalance;
        this.frozen = false;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void freeze() {
        this.frozen = true;
    }

    public void unfreeze() {
        this.frozen = false;
    }

    protected void credit(BigDecimal amount) {
        validateActive();
        this.balance = this.balance.add(amount);
    }

    protected void debit(BigDecimal amount) {
        validateActive();
        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        this.balance = this.balance.subtract(amount);
    }

    protected void validateActive() {
        if (frozen) {
            throw new AccountFrozenException("Account is frozen");
        }
    }

    public abstract void applyMonthlyInterest();
}
