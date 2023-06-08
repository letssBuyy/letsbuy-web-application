package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.entities.Tracking;
import com.application.letsbuy.internal.services.TrackingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/trackings")
public class TrackingController {

    private final TrackingService trackingService;

    @PostMapping("/{idUser}/{idAdvertisement}")
    private ResponseEntity<List<Tracking>> create(@PathVariable final Long idUser, @PathVariable final Long idAdvertisement,
                                                  @RequestBody final Tracking tracking) {
        return new ResponseEntity<>(this.trackingService.create(idUser, idAdvertisement, tracking), HttpStatus.CREATED);
    }
}
