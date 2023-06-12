package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.AdversimentsLike;
import com.application.letsbuy.internal.enums.AdversimentColorEnum;
import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.QualityEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserAdversimentsDto {
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
    private List<ImageDtoResponse> images;
    private Boolean isLike;
    private Long likeId;

    public UserAdversimentsDto(Adversiment adversiment) {
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
        if (adversiment.getImages() != null && !adversiment.getImages().isEmpty()) {
            this.images = ImageDtoResponse.convert(adversiment.getImages());
        }
        this.isLike = false;
        this.likeId = null;
    }

    public UserAdversimentsDto(Adversiment adversiment,  List<AdversimentsLike> adversimentsLikes) {
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
        this.isLike = false;
        for (AdversimentsLike likeAdversiment : adversimentsLikes) {
                if (adversiment.getId().equals(likeAdversiment.getAdversiment().getId())) {
                    this.isLike = true;
                    this.likeId = likeAdversiment.getId();
                }
        }
    }
}
