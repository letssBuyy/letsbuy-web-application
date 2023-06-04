package com.application.letsbuy.internal.dto;


import com.application.letsbuy.internal.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto {

    private UserDtoResponse user;

    private String token;

    private String tipo;

    public TokenDto(User user, String token, String tipo) {
        this.user = new UserDtoResponse(user);
        this.token = token;
        this.tipo = tipo;
    }

}
