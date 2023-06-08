package com.application.letsbuy.internal.repositories;

import com.application.letsbuy.internal.entities.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {
}
