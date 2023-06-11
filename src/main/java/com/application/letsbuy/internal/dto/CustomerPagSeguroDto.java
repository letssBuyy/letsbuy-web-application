package com.application.letsbuy.internal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerPagSeguroDto {

    private String name;

    private String email;

    @JsonProperty("tax_id")
    private String taxId;

    private List<PhonePagSeguroDto> phones;
}
