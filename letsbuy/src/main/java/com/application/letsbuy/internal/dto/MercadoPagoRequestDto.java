package com.application.letsbuy.internal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MercadoPagoRequestDto {

    private String name;

    private String email;

    private Long idUser;

    private String descriptionAdvertisement;
}
