package com.ebuka.bankingapi.model.repository;

import com.ebuka.bankingapi.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.createdAt BETWEEN :from AND :to")
    List<Transaction> getTransactionsBetween(LocalDateTime from, LocalDateTime to);

    boolean existsByAccount_AccountNumber(String accountNumber);
}
