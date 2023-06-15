package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.Tracking;
import com.application.letsbuy.internal.enums.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AdversimentDtoResponse {

    private Long userId;
    private Long id;
    private String title;
    private String description;
    private Double price;
    private LocalDate postDate;
    private LocalDate lastUpdate;
    private LocalDate saleDate;
    private CategoryEnum category;
    private QualityEnum quality;
    private AdversimentColorEnum color;
    private AdversimentEnum isActive;
    private AdversimentEnum contest;
    private List<ImageDtoResponse> images;
    private List<TrackingResponseDto> trackings;
    private PaymentUserAdvertisementResponseDto paymentUserAdversiment;
    private UserDtoResponse buyer;

    public static List<AdversimentDtoResponse> convert(List<Adversiment> adversiments) {
        return adversiments.stream().filter(adversiment -> adversiment.getUser().getIsActive() != ActiveInactiveEnum.INACTIVE)
                .map(AdversimentDtoResponse::new).toList();
    }

    public AdversimentDtoResponse(Adversiment adversiment) {
        this.userId = adversiment.getUser().getId();
        this.id = adversiment.getId();
        this.title = adversiment.getTitle();
        this.description = adversiment.getDescription();
        this.price = adversiment.getPrice();
        this.postDate = adversiment.getPostDate();
        this.lastUpdate = adversiment.getLastUpdate();
        this.saleDate = adversiment.getSaleDate();
        this.category = adversiment.getCategory();
        this.quality = adversiment.getQuality();
        this.color = adversiment.getColor();
        this.isActive = adversiment.getIsActive();
        this.contest = adversiment.getContest();
        if (adversiment.getTrackings() != null && !adversiment.getTrackings().isEmpty()) {
            this.convertTrackings(adversiment.getTrackings());
        }
        if (adversiment.getImages() != null && !adversiment.getImages().isEmpty()){
            this.images = ImageDtoResponse.convert(adversiment.getImages());
        }
    }

    private void convertTrackings(List<Tracking> trackings) {
        List<TrackingResponseDto> trackingResponseDtos = TrackingResponseDto.parseListEntityToListDto(trackings);
        this.setTrackings(trackingResponseDtos);
    }

    public AdversimentDtoResponse(Adversiment adversiment, PaymentUserAdvertisementResponseDto paymentUserAdversiment, UserDtoResponse user) {
        this.userId = adversiment.getUser().getId();
        this.id = adversiment.getId();
        this.title = adversiment.getTitle();
        this.description = adversiment.getDescription();
        this.price = adversiment.getPrice();
        this.postDate = adversiment.getPostDate();
        this.lastUpdate = adversiment.getLastUpdate();
        this.saleDate = adversiment.getSaleDate();
        this.category = adversiment.getCategory();
        this.quality = adversiment.getQuality();
        this.color = adversiment.getColor();
        this.isActive = adversiment.getIsActive();
        this.contest = adversiment.getContest();
        if (adversiment.getTrackings() != null && !adversiment.getTrackings().isEmpty()) {
            this.convertTrackings(adversiment.getTrackings());
        }
        if (adversiment.getImages() != null && !adversiment.getImages().isEmpty()){
            this.images = ImageDtoResponse.convert(adversiment.getImages());
        }
        this.paymentUserAdversiment = paymentUserAdversiment;
        this.buyer = user;
    }
}
