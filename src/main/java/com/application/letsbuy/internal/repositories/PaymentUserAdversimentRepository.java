package com.application.letsbuy.internal.repositories;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.PaymentUserAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentUserAdversimentRepository extends JpaRepository<PaymentUserAdvertisement, Long> {

    Optional<PaymentUserAdvertisement> findByAdversiment(Adversiment adversiment);
}
