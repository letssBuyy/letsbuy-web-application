package com.application.letsbuy.internal.repositories;

import com.application.letsbuy.internal.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findByUserId(Long userId);

}
