package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.enums.AdversimentColorEnum;
import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.QualityEnum;
import com.application.letsbuy.internal.services.AdversimentService;
import com.application.letsbuy.internal.services.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AdversimentDto {

    @NotNull
    private Long userId;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private Double price;
    @NotNull
    private AdversimentColorEnum color;
    @NotNull
    private CategoryEnum category;
    @NotNull
    private QualityEnum quality;

    public Adversiment convert(UserService userService) {
        User user = userService.findById(userId);
        return new Adversiment(user, title, description, price, color, category, quality);
    }

    public Adversiment update(Long id, AdversimentService adversimentService) {
        Adversiment adversiment = adversimentService.findById(id);
        adversiment.setTitle(title);
        adversiment.setDescription(description);
        adversiment.setPrice(price);
        adversiment.setPostDate(adversiment.getPostDate());
        adversiment.setLastUpdate(LocalDate.now());
        adversiment.setCategory(category);
        adversiment.setQuality(quality);
        adversiment.setColor(color);
        return adversiment;
    }
}
