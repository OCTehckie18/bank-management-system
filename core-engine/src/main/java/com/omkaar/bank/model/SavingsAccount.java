package com.omkaar.bank.model;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

    private static final BigDecimal INTEREST_RATE = new BigDecimal("0.03");

    public SavingsAccount(BigDecimal initialBalance) {
        super(initialBalance);
    }

    @Override
    public void applyMonthlyInterest() {
        BigDecimal interest = balance.multiply(INTEREST_RATE);
        credit(interest);
    }
}
