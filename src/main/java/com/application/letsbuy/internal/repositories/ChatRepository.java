package com.application.letsbuy.internal.repositories;

import com.application.letsbuy.internal.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {

    List<Chat> findChatBySellerIdOrBuyerId (Long idSeller, Long idBuyer);

    Boolean existsChatBySellerIdAndBuyerIdAndAdversimentId (Long idSeller, Long idBuyer, Long adversimentId);

    Optional<Chat> findChatBySellerIdAndBuyerIdAndAdversimentId (Long idSeller, Long idBuyer, Long adversimentId);
}
