package com.application.letsbuy.internal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargesPagSeguroDto {

    private String id;

    @JsonProperty("reference_id")
    private String referenceId;

    private String description;

    private String status;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("paid_at")
    private String paidAt;

    private AmountPagSeguroDto amount;

    @JsonProperty("payment_method")
    private PaymentMethodPagSeguroDto paymentMethod;

    @JsonProperty("notification_urls")
    private List<String> notificationUrls;

}
