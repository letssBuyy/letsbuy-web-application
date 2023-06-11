package com.application.letsbuy.internal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemsPagSeguroDto {

    @JsonProperty("reference_id")
    private String referenceId;

    private String name;

    private Integer quantity;

    @JsonProperty("unit_amount")
    private Integer unitAmount;

}
