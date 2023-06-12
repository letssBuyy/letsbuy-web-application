package com.application.letsbuy.internal.repositories;

import com.application.letsbuy.internal.entities.AdversimentsLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdversimentsLikeRepository extends JpaRepository<AdversimentsLike, Long> {
    List<AdversimentsLike> findByUserId(Long id);

    Optional<AdversimentsLike> findAdversimentLikeByAdversimentIdAndUserId(Long adversimentId, Long userId);
}
