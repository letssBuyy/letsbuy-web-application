package com.application.letsbuy.internal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDto {

    private Double amount;

    private String status;

    private String description;

    private String externalReference;

    private LocalDateTime createdAd;

    private LocalDateTime paymentDate;

    private String initPoint;

    private String cupomUser;
}
