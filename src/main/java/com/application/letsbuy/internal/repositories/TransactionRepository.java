package com.application.letsbuy.internal.repositories;

import com.application.letsbuy.internal.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserId(Long UserId);
}
