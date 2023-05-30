package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.enums.ActiveInactiveEnum;
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
public class UserAdversimentsDtoResponse {

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
    private String profileImage;
    private ActiveInactiveEnum isActive;
    private List<UserAdversimentsDto> adversiments;

    public UserAdversimentsDtoResponse(User user) {
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
        this.profileImage = user.getProfileImage();
        this.isActive = user.getIsActive();
        this.adversiments = new ArrayList<>();
        this.adversiments.addAll(user.getAdversiments().stream().map(UserAdversimentsDto::new).collect(Collectors.toList()));
    }
}
