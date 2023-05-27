package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.enums.ActiveInactiveEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserDtoResponse {

    private Long id;
    private String name;
    private String email;
    private String cpf;
    private LocalDate birthDate;
    private String phoneNumber;
    private String cep;
    private String road;
    private Long number;
    private String complement;
    private ActiveInactiveEnum isActive;
    private String profileImage;

    public UserDtoResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.birthDate = user.getBirthDate();
        this.phoneNumber = user.getPhoneNumber();
        this.cep = user.getCep();
        this.road = user.getRoad();
        this.number = user.getNumber();
        this.complement = user.getComplement();
        this.isActive = user.getIsActive();
        this.profileImage = user.getProfileImage();
    }
}
