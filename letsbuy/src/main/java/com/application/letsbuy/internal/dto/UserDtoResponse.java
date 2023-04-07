package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDtoResponse {

    private String name;
    private String email;
    private String cpf;
    private LocalDate birthDate;
    private String phoneNumber;

    public UserDtoResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.birthDate = user.getBirthDate();
        this.phoneNumber = user.getPhoneNumber();
    }
}
