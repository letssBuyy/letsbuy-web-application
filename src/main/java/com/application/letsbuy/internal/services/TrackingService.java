package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.Tracking;
import com.application.letsbuy.internal.enums.TrackingStatus;
import com.application.letsbuy.internal.exceptions.TrackingBadRequestException;
import com.application.letsbuy.internal.exceptions.TrackingConflictException;
import com.application.letsbuy.internal.repositories.TrackingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Track;
import java.lang.annotation.Target;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class TrackingService {

    private final TrackingRepository trackingRepository;
    private final AdversimentService adversimentService;

    public List<Tracking> create(Long idUser, Long idAdvertisement, Tracking tracking) {
        if (this.validateTracking(idUser, idAdvertisement, tracking)) {
            if (TrackingStatus.DELIVERED.equals(tracking.getStatus())) {
                return List.of(this.trackingRepository.save(tracking));
            }
            return this.trackingRepository.saveAll(this.createTrackings(tracking, idAdvertisement));
        }
        throw new TrackingBadRequestException("Não foi possível criar um tracking verifique os dados e tente novamente");
    }

    public Tracking update(Tracking tracking) {
        return this.trackingRepository.save(tracking);
    }

    private boolean validateTracking(Long idUser, Long idAdvertisement, Tracking tracking) {
        return switch (tracking.getStatus()) {
            case SENT -> verifyTrackingSeller(idUser, idAdvertisement, tracking);
            case DELIVERED -> verifyTrackingBuyer(idUser, idAdvertisement, tracking);
            default ->
                    throw new TrackingBadRequestException("Não foi possivel Achar um status compativel com o que foi enviado");
        };
    }

    private boolean verifyTrackingSeller(Long idUser, Long idAdvertisement, Tracking tracking) {
        Adversiment adversiment = this.adversimentService.findById(idAdvertisement);
        Optional<Tracking> trackingFind = adversiment.getTrackings().stream().filter(t -> t.getStatus().equals(tracking.getStatus())).findAny();
        if (trackingFind.isPresent()) {
            throw new TrackingConflictException(String.format("tracking já cadastrado: %s", tracking.getStatus().name()));
        }
        // validando se o id enviado e do seller que criou o anuncio
        return Objects.equals(adversiment.getUser().getId(), idUser);
    }

    private boolean verifyTrackingBuyer(Long idUser, Long idAdvertisement, Tracking tracking) {
        Adversiment adversiment = this.adversimentService.findById(idAdvertisement);
        Optional<Tracking> trackingFind = adversiment.getTrackings().stream().filter(t -> t.getStatus().equals(tracking.getStatus())).findAny();
        if (trackingFind.isPresent()) {
            throw new TrackingConflictException(String.format("tracking já cadastrado: %s", tracking.getStatus().name()));
        }
        return !Objects.equals(adversiment.getUser().getId(), idUser);
    }

    //IN_TRANSIT, WAITING_FOR_RECEIPT_CONFIRMATION
    private Set<Tracking> createTrackings(Tracking fromTracking, Long idAdvertisement) {

        Adversiment adversiment = this.adversimentService.findById(idAdvertisement);
        
        Set<Tracking> trackings = new HashSet<>();
        
        Tracking trackingInTransit = new Tracking();
        trackingInTransit.setAdversiment(adversiment);
        trackingInTransit.setStatus(TrackingStatus.IN_TRANSIT);

        Tracking trackingWaitingForReceiptConfirmation = new Tracking();
        trackingInTransit.setAdversiment(adversiment);
        trackingInTransit.setStatus(TrackingStatus.WAITING_FOR_RECEIPT_CONFIRMATION);

        trackings.add(fromTracking);
        trackings.add(trackingInTransit);
        trackings.add(trackingWaitingForReceiptConfirmation);
        
        return trackings;
    }
}
