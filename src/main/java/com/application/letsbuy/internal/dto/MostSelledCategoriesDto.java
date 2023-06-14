package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MostSelledCategoriesDto {
    CategoryEnum category;
    Long quantity;
}
