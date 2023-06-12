package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.Image;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.enums.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    private PaymentUserAdvertisementResponseDto paymentUserAdversiment;
    private UserDtoResponse buyer;

    public static List<AdversimentDtoResponse> convert(List<Adversiment> adversiments) {
        return adversiments.stream().filter(a -> a.getUser().getIsActive() != ActiveInactiveEnum.INACTIVE)
                .map(AdversimentDtoResponse::new).collect(Collectors.toList());
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
        if (adversiment.getImages() != null && !adversiment.getImages().isEmpty()){
            this.images = ImageDtoResponse.convert(adversiment.getImages());
        }
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
        if (adversiment.getImages() != null && !adversiment.getImages().isEmpty()){
            this.images = ImageDtoResponse.convert(adversiment.getImages());
        }
        this.paymentUserAdversiment = paymentUserAdversiment;
        this.buyer = user;
    }
}
