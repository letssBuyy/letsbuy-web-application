package com.application.letsbuy.internal.repositories;

import com.application.letsbuy.internal.entities.BankAccountUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountUserRepository extends JpaRepository<BankAccountUser, Long> {

}
