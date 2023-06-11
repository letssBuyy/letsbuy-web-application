package com.application.letsbuy.internal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AmountPagSeguroDto {

    private Integer value;

    private String currency;
}
