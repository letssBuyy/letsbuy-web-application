package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Adversiment;
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
public class AdversimentDtoResponse {

    private Long id;
    private String title;

    private String description;

    private Double price;

    private LocalDate postDate;

    private LocalDate lastUpdate;

    private LocalDate saleDate;

    private CategoryEnum category;

    private QualityEnum quality;

    private Integer priority;



    public static List<AdversimentDtoResponse> convert(List<Adversiment> adversiments) {
        return adversiments.stream().map(AdversimentDtoResponse::new).collect(Collectors.toList());
    }

    public AdversimentDtoResponse(Adversiment adversiment) {
        this.id = adversiment.getId();
        this.title = adversiment.getTitle();
        this.description = adversiment.getDescription();
        this.price = adversiment.getPrice();
        this.postDate = adversiment.getPostDate();
        this.lastUpdate = adversiment.getLastUpdate();
        this.saleDate = adversiment.getSaleDate();
        this.category = adversiment.getCategory();
        this.quality = adversiment.getQuality();
    }
}
