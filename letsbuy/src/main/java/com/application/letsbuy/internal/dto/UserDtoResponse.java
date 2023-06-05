package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.enums.ActiveInactiveEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String neighborhood;
    private String complement;
    private String state;
    private String city;
    private ActiveInactiveEnum isActive;
    private String profileImage;
    private LocalDateTime registrationDate;

    private Double balance;

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
        this.neighborhood = user.getNeighborhood();
        this.complement = user.getComplement();
        this.state = user.getState();
        this.city = user.getCity();
        this.isActive = user.getIsActive();
        this.profileImage = user.getProfileImage();
        this.registrationDate = user.getRegistrationDate();
        this.balance = user.getBalance();
    }
}
