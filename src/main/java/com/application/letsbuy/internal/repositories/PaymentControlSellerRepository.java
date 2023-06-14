package com.application.letsbuy.internal.repositories;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.PaymentControllSeller;
import com.application.letsbuy.internal.entities.PaymentUserAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentControlSellerRepository extends JpaRepository<PaymentControllSeller, Long> {

    Optional<PaymentControllSeller> findByPaymentUserAdvertisementAdversimentId(Long idAdversiment);

    List<PaymentControllSeller> findByPaymentUserAdvertisementBuyerId(Long idBuyer);
}
