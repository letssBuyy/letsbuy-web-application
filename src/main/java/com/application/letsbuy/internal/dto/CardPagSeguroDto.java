package com.application.letsbuy.internal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardPagSeguroDto {

    private String number;

    @JsonProperty("exp_month")
    private String expMonth;

    @JsonProperty("exp_year")
    private String expYear;

    @JsonProperty("security_code")
    private String securityCode;

    private Boolean store;

    private HolderPagSeguroDto holder;
}
