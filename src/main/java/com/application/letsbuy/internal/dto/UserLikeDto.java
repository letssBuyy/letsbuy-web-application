package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.enums.AdversimentColorEnum;
import com.application.letsbuy.internal.enums.AdversimentEnum;
import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.QualityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLikeDto {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private LocalDate postDate;
    private LocalDate lastUpdate;
    private LocalDate saleDate;
    private AdversimentColorEnum color;
    private CategoryEnum category;
    private QualityEnum quality;
    private AdversimentEnum isActive;
    private AdversimentEnum contest;
    private UserSellerLikeDto userSellerLikeDto;

    public UserLikeDto(Adversiment adversiment) {
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
        this.userSellerLikeDto = new UserSellerLikeDto(adversiment.getUser());
    }

    public UserLikeDto(Adversiment adversiment, Long quantityTotalAdversiment, Long quantityAdversimentSolded, Long quantityAdversimentActive) {
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
        this.userSellerLikeDto = new UserSellerLikeDto(adversiment.getUser(), quantityTotalAdversiment, quantityAdversimentSolded, quantityAdversimentActive);
    }
}
