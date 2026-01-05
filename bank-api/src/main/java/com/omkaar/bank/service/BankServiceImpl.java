package com.omkaar.bank.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omkaar.bank.entity.AccountEntity;
import com.omkaar.bank.entity.TransactionEntity;
import com.omkaar.bank.mapper.AccountMapper;
import com.omkaar.bank.mapper.TransactionMapper;
import com.omkaar.bank.model.Account;
import com.omkaar.bank.model.TransactionView;
import com.omkaar.bank.repository.AccountRepository;
import com.omkaar.bank.repository.TransactionRepository;

@Service
public class BankServiceImpl implements BankOperations {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankServiceImpl(AccountRepository accountRepository,
            TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public void registerAccount(Account account) {
        AccountEntity entity = AccountMapper.toEntity(account);
        if (entity != null) {
            accountRepository.save(entity);
        } else {
            throw new IllegalArgumentException("Failed to map account to entity");
        }
    }

    @Override
    @Transactional
    public void deposit(UUID accountId, BigDecimal amount) {
        if (accountId == null) {
            throw new IllegalArgumentException("Account ID cannot be null");
        }

        AccountEntity entity = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Account domain = AccountMapper.toDomain(entity);
        domain.deposit(amount);

        entity.setBalance(domain.getBalance());
        accountRepository.save(entity);

        TransactionEntity tx = TransactionMapper.deposit(accountId, amount);
        if (tx != null) {
            transactionRepository.save(tx);
        }
    }

    @Override
    @Transactional
    public void withdraw(UUID accountId, BigDecimal amount) {
        if (accountId == null) {
            throw new IllegalArgumentException("Account ID cannot be null");
        }

        AccountEntity entity = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Account domain = AccountMapper.toDomain(entity);
        domain.withdraw(amount);

        entity.setBalance(domain.getBalance());
        accountRepository.save(entity);

        TransactionEntity tx = TransactionMapper.withdraw(accountId, amount);
        if (tx != null) {
            transactionRepository.save(tx);
        }
    }

    @Override
    @Transactional
    public void transfer(UUID fromId, UUID toId, BigDecimal amount) {
        if (fromId == null || toId == null) {
            throw new IllegalArgumentException("Account IDs cannot be null");
        }

        AccountEntity fromEntity = accountRepository.findById(fromId)
                .orElseThrow(() -> new IllegalArgumentException("Source account not found"));

        AccountEntity toEntity = accountRepository.findById(toId)
                .orElseThrow(() -> new IllegalArgumentException("Target account not found"));

        Account from = AccountMapper.toDomain(fromEntity);
        Account to = AccountMapper.toDomain(toEntity);

        from.withdraw(amount);
        to.deposit(amount);

        fromEntity.setBalance(from.getBalance());
        toEntity.setBalance(to.getBalance());

        accountRepository.save(fromEntity);
        accountRepository.save(toEntity);

        TransactionEntity tx = TransactionMapper.transfer(fromId, toId, amount);
        if (tx != null) {
            transactionRepository.save(tx);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionView> getTransactionHistory(UUID accountId) {
        if (accountId == null) {
            throw new IllegalArgumentException("Account ID cannot be null");
        }
        return transactionRepository
                .findByFromAccountIdOrToAccountId(accountId, accountId)
                .stream()
                .map(TransactionMapper::toView)
                .toList();
    }

    // Temporary fix
    @Override
    public void requestLoan(UUID accountId, BigDecimal amount) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void processNextLoan() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void undoLastTransaction() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
