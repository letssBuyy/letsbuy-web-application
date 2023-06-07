package com.application.letsbuy.internal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ChatRequestDto {

    private Long idSeller;
    private Long idBuyer;
    private Long idAdversiment;
}
