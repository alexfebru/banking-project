package com.bank.bankingproject.repository;

import com.bank.bankingproject.model.Account;
import com.bank.bankingproject.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByAccountIdOrderByTransactionDateDesc(UUID accountId);
}
