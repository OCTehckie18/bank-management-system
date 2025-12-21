package com.omkaar.bank.model;

import java.math.BigDecimal;

public class CurrentAccount extends Account {

    public CurrentAccount(BigDecimal initialBalance) {
        super(initialBalance);
    }

    @Override
    public void applyMonthlyInterest() {
        // No interest for current accounts
    }
}
