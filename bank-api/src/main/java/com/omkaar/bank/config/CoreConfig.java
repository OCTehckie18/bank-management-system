package com.omkaar.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.omkaar.bank.repository.AccountRepository;
import com.omkaar.bank.repository.TransactionRepository;
import com.omkaar.bank.service.BankOperations;
import com.omkaar.bank.service.BankServiceImpl;

@Configuration
public class CoreConfig {

    @Bean
    public BankOperations bankOperations(AccountRepository ar,
            TransactionRepository tr) {
        return new BankServiceImpl(ar, tr);
    }

}
