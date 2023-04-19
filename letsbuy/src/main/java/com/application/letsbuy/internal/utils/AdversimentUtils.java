package com.application.letsbuy.internal.utils;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.QualityEnum;

import java.time.LocalDate;

public final class AdversimentUtils {
    private AdversimentUtils() {
    }
    public static Adversiment mockAdversiment() {
        Adversiment adversiment = new Adversiment();
        adversiment.setId(1000L);
        adversiment.setUser(UserUtils.mockUser());
        adversiment.setTitle("IPHONE 14");
        adversiment.setDescription("Celular novo, mal foi usado.");
        adversiment.setPrice(7000.0);
        adversiment.setPostDate(LocalDate.now());
        adversiment.setLastUpdate(LocalDate.now());
        adversiment.setSaleDate(LocalDate.now());
        adversiment.setCategory(CategoryEnum.ELECTRONICS);
        adversiment.setQuality(QualityEnum.SEMI_NEW);
        return adversiment;
    }
}
