package com.application.letsbuy.internal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentUserAdvertisementRequestDto {
    private Boolean isShipment;

    private Long idAdvertisement;

    private Long idUser;

    private String cardNumber;

    private String expMonth;

    private String expYear;

    private String securityCode;

    private String holderName;
}
