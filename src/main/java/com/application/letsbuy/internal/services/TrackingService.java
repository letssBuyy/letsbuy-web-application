package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.entities.Tracking;
import com.application.letsbuy.internal.repositories.TrackingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class TrackingService {
    private final TrackingRepository trackingRepository;

    public Tracking create(Tracking tracking) {
        return this.trackingRepository.save(tracking);
    }

    public Tracking update(Tracking tracking) {
        return this.trackingRepository.save(tracking);
    }
}
