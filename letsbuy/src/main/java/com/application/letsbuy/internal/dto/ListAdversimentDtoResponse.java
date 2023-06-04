package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.enums.ActiveInactiveEnum;
import com.application.letsbuy.internal.enums.AdversimentEnum;
import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.QualityEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ListAdversimentDtoResponse {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private LocalDate postDate;
    private LocalDate lastUpdate;
    private LocalDate saleDate;
    private CategoryEnum category;
    private QualityEnum quality;
    private AdversimentEnum isActive;
    private AdversimentEnum contest;
    private List<ImageDtoResponse> images;
    private UserSellerLikeDto user;

    public static List<ListAdversimentDtoResponse> convert(List<Adversiment> adversiments) {
        return adversiments.stream().filter(a -> a.getUser().getIsActive() != ActiveInactiveEnum.INACTIVE)
                .map(com.application.letsbuy.internal.dto.ListAdversimentDtoResponse::new).collect(Collectors.toList());
    }

    public ListAdversimentDtoResponse(Adversiment adversiment) {
        this.id = adversiment.getId();
        this.title = adversiment.getTitle();
        this.description = adversiment.getDescription();
        this.price = adversiment.getPrice();
        this.postDate = adversiment.getPostDate();
        this.lastUpdate = adversiment.getLastUpdate();
        this.saleDate = adversiment.getSaleDate();
        this.category = adversiment.getCategory();
        this.quality = adversiment.getQuality();
        this.isActive = adversiment.getIsActive();
        this.contest = adversiment.getContest();
        if (adversiment.getImages() != null && !adversiment.getImages().isEmpty()) {
            this.images = ImageDtoResponse.convert(adversiment.getImages());
        }
        this.user = new UserSellerLikeDto(adversiment.getUser());
    }

    public ListAdversimentDtoResponse(Adversiment adversiment, Long quantityTotalAdversiment, Long quantityAdversimentSolded, Long quantityAdversimentActive) {
        this.id = adversiment.getId();
        this.title = adversiment.getTitle();
        this.description = adversiment.getDescription();
        this.price = adversiment.getPrice();
        this.postDate = adversiment.getPostDate();
        this.lastUpdate = adversiment.getLastUpdate();
        this.saleDate = adversiment.getSaleDate();
        this.category = adversiment.getCategory();
        this.quality = adversiment.getQuality();
        this.isActive = adversiment.getIsActive();
        this.contest = adversiment.getContest();
        if (adversiment.getImages() != null && !adversiment.getImages().isEmpty()) {
            this.images = ImageDtoResponse.convert(adversiment.getImages());
        }
        this.user = new UserSellerLikeDto(adversiment.getUser(), quantityTotalAdversiment, quantityAdversimentSolded, quantityAdversimentActive);
    }
}
