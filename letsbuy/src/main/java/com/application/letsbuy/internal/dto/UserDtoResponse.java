package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public UserDtoResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.birthDate = user.getBirthDate();
        this.phoneNumber = user.getPhoneNumber();
    }
}
