package com.omkaar.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.omkaar.bank.service.BankOperations;
import com.omkaar.bank.service.BankService;

@Configuration
public class CoreConfig {

    @Bean
    public BankOperations bankOperations() {
        return new BankService();
    }
}
