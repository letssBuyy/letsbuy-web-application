package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.BankAccount;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.enums.AccessLevelEnum;
import com.application.letsbuy.internal.enums.ActiveInactiveEnum;
import com.application.letsbuy.internal.enums.AdversimentEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime registrationDate;
    private Long quantityTotalAdversiment;
    private Long quantityTotalSolded;
    private Long quantityTotalActive;
    private Double balance;
    private BankAccount bankAccount;

    private AccessLevelEnum accessLevel;
    private List<UserAdversimentsDto> adversiments;

    public UserAdversimentsDtoResponse(User user, Long quantityTotalAdversiment, Long quantityTotalActive,Long quantityTotalSolded) {
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
        this.registrationDate = user.getRegistrationDate();
        this.quantityTotalAdversiment = quantityTotalAdversiment;
        this.quantityTotalActive = quantityTotalActive;
        this.quantityTotalSolded = quantityTotalSolded;
        if (user.getBankAccount() != null && !user.getBankAccount().isEmpty()){
            this.bankAccount = user.getBankAccount().get(0);
        }
        this.balance = user.getBalance();
        this.accessLevel = user.getAccessLevel();
        this.adversiments = new ArrayList<>();
        this.adversiments.addAll(user.getAdversiments().stream().filter(adversiment -> adversiment.getIsActive() == AdversimentEnum.ACTIVE).map(UserAdversimentsDto::new).collect(Collectors.toList()));
    }
}
