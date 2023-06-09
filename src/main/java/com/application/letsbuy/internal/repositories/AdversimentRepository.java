package com.application.letsbuy.internal.repositories;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.enums.AdversimentEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdversimentRepository extends JpaRepository<Adversiment, Long> {
    Long countByUserId(Long id);

    Long countByUserIdAndIsActive(Long id, AdversimentEnum isActive);

    List<Adversiment> findByTitleContainsIgnoreCaseAndIsActive(Optional<String> title, AdversimentEnum isActive);
}
