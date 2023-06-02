package com.application.letsbuy.internal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDto {

    private BigDecimal transactionAmount;

    private String transactionStatus;

    private String transactionDescription;

    private String transactionExternalReference;

    private LocalDate expirationDate;

    private String initPoint;

    private String cupomUser;
}
