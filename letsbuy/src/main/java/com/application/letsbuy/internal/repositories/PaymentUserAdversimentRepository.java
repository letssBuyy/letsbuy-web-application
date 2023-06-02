package com.application.letsbuy.internal.repositories;

import com.application.letsbuy.internal.entities.PaymentUserAdverstisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentUserAdversimentRepository extends JpaRepository<PaymentUserAdverstisement, Long> {
}
