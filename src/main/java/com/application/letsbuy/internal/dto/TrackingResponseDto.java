package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Tracking;
import com.application.letsbuy.internal.enums.TrackingStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TrackingResponseDto {

    private TrackingStatus status;

    private LocalDateTime createdDate;

    private Long idAdversiment;

    public static List<TrackingResponseDto> parseListEntityToListDto(List<Tracking> trackings) {
        return trackings.stream().map(TrackingResponseDto::parseEntityToDto).toList();
    }

    public static TrackingResponseDto parseEntityToDto(Tracking tracking) {
        Long idAdvertisement = tracking.getAdversiment().getId();
        TrackingResponseDto responseDto = new TrackingResponseDto();
        responseDto.setStatus(tracking.getStatus());
        responseDto.setCreatedDate(tracking.getCreatedDate());
        responseDto.setIdAdversiment(idAdvertisement);
        return responseDto;
    }
}
