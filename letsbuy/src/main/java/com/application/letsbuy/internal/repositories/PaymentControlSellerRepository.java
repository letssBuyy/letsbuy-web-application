package com.application.letsbuy.internal.repositories;

import com.application.letsbuy.internal.entities.PaymentControllSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentControlSellerRepository extends JpaRepository<PaymentControllSeller, Long> {
}
