package com.application.letsbuy.internal.repositories;

import com.application.letsbuy.internal.entities.PaymentUserAdvertisement;
import com.application.letsbuy.internal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentUserAdversimentRepository extends JpaRepository<PaymentUserAdvertisement, Long> {

    Optional<PaymentUserAdvertisement> findByAdversimentId(Long idAdversiment);
}
