package com.application.letsbuy.internal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageRequestDto {

    private Long idChat;
    private String message;

    private Long idUser;
}
