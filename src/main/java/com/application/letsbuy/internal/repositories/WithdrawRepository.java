package com.application.letsbuy.internal.repositories;

import com.application.letsbuy.internal.entities.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {

    List<Withdraw> findByUserId(Long UserId);
}
