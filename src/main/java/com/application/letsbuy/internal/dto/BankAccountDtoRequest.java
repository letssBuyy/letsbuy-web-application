package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.BankAccount;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.services.BankAccountService;
import com.application.letsbuy.internal.services.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class BankAccountDtoRequest {
    @NotNull
    private Long userId;

    @NotBlank
    private String bankNumber;

    @NotBlank
    private String agencyNumber;

    @NotBlank
    private String accountNumber;


    public BankAccount convert(UserService userService) {
        User user = userService.findById(userId);
        return new BankAccount(user, bankNumber, agencyNumber, accountNumber);
    }
}
