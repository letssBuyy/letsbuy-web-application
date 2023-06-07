package com.application.letsbuy.internal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageProposalRequestDto {

    private Long idChat;
    private Long idUser;
    private Double amount;
}
