package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.Tracking;
import com.application.letsbuy.internal.enums.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ListAdversimentTrackingDtoResponse {

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
    private UserSellerLikeDto user;

    public static List<ListAdversimentTrackingDtoResponse> convert(List<Adversiment> adversiments) {
        return adversiments.stream().filter(a -> a.getUser().getIsActive() != ActiveInactiveEnum.INACTIVE)
                .map(ListAdversimentTrackingDtoResponse::new).collect(Collectors.toList());
    }

    public ListAdversimentTrackingDtoResponse(Adversiment adversiment) {
        this.id = adversiment.getId();
        this.title = adversiment.getTitle();
        this.description = adversiment.getDescription();
        this.price = adversiment.getPrice();
        this.postDate = adversiment.getPostDate();
        this.lastUpdate = adversiment.getLastUpdate();
        this.saleDate = adversiment.getSaleDate();
        this.color = adversiment.getColor();
        this.category = adversiment.getCategory();
        this.quality = adversiment.getQuality();
        this.isActive = adversiment.getIsActive();
        this.contest = adversiment.getContest();
        if (adversiment.getImages() != null && !adversiment.getImages().isEmpty()) {
            this.images = ImageDtoResponse.convert(adversiment.getImages());
        }
        if (adversiment.getTrackings() != null && !adversiment.getTrackings().isEmpty()) {
            this.trackings = TrackingResponseDto.parseListEntityToListDto(adversiment.getTrackings());
        }
        this.user = new UserSellerLikeDto(adversiment.getUser());
    }

    public ListAdversimentTrackingDtoResponse(Adversiment adversiment, Long quantityTotalAdversiment, Long quantityAdversimentSolded, Long quantityAdversimentActive) {
        this.id = adversiment.getId();
        this.title = adversiment.getTitle();
        this.description = adversiment.getDescription();
        this.price = adversiment.getPrice();
        this.postDate = adversiment.getPostDate();
        this.lastUpdate = adversiment.getLastUpdate();
        this.saleDate = adversiment.getSaleDate();
        this.color = adversiment.getColor();
        this.category = adversiment.getCategory();
        this.quality = adversiment.getQuality();
        this.isActive = adversiment.getIsActive();
        this.contest = adversiment.getContest();
        if (adversiment.getImages() != null && !adversiment.getImages().isEmpty()) {
            this.images = ImageDtoResponse.convert(adversiment.getImages());
        }
        if (adversiment.getTrackings() != null && !adversiment.getTrackings().isEmpty()) {
            this.trackings = TrackingResponseDto.parseListEntityToListDto(adversiment.getTrackings());
        }
        this.user = new UserSellerLikeDto(adversiment.getUser(), quantityTotalAdversiment, quantityAdversimentSolded, quantityAdversimentActive);
    }

}
