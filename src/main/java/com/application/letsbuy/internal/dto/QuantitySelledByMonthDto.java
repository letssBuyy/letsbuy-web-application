package com.application.letsbuy.internal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuantitySelledByMonthDto {

    private int month;
    private long quantity;


}
