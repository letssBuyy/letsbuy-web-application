package com.application.letsbuy.internal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentMethodPagSeguroDto {

    private String type;

    private Integer installments;

    private Boolean capture;

    private CardPagSeguroDto card;

    @JsonProperty("soft_descriptor")
    private String softDescriptor;
}
