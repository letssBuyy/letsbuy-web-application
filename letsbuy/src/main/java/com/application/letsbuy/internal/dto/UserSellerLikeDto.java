package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSellerLikeDto {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;

    public UserSellerLikeDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }
}
