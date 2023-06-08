package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.enums.AdversimentColorEnum;
import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.QualityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdversimentFilterDto {

    private String city;

    private Double priceMin;

    private Double priceMax;

    private QualityEnum quality;

    private CategoryEnum category;

    private AdversimentColorEnum color;

    private Integer filter;
}
