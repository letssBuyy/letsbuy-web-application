package com.application.letsbuy.internal.repositories;

import com.application.letsbuy.internal.entities.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {
}
