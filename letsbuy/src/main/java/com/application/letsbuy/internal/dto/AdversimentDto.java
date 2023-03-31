package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.QualityEnum;
import com.application.letsbuy.internal.services.AdversimentService;
import com.application.letsbuy.internal.services.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdversimentDto {

    private String title;

    private String description;

    private Double price;

    private String postDate;

    private String lastUpdate;

    private String saleDate;

    private CategoryEnum category;

    private QualityEnum quality;

    private Integer priority;


    public Adversiment convert() {
        return new Adversiment(title, description, price, postDate, lastUpdate, saleDate, category, quality, priority);
    }

    public Adversiment update(Long id, AdversimentService adversimentService) {
        Adversiment adversiment = adversimentService.findById(id);
        adversiment.setTitle(title);
        adversiment.setDescription(description);
        adversiment.setPrice(price);
        adversiment.setPostDate(postDate);
        adversiment.setLastUpdate(lastUpdate);
        adversiment.setSaleDate(saleDate);
        adversiment.setCategory(category);
        adversiment.setQuality(quality);
        adversiment.setPriority(priority);
        return adversiment;
    }
}
