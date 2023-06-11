package com.application.letsbuy.internal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagSeguroDto {

    private String id;

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("created_at")
    private String createdAt;

    private CustomerPagSeguroDto customer;

    private List<ItemsPagSeguroDto> items;

    @JsonProperty("notification_urls")
    private List<String> notificationUrls;

    private List<ChargesPagSeguroDto> charges;

    private ShippingPagSeguroDto shipping;

}
