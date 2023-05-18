package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.BankAccountUser;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.services.AdversimentService;
import com.application.letsbuy.internal.services.BankAccountUserService;
import com.application.letsbuy.internal.services.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class BankAccountUserDtoRequest {
    @NotNull
    private Long userId;
    @NotBlank
    private String accountNumber;
    @NotBlank
    private String accountDigit;
    @NotNull
    private Boolean isMain;


    public BankAccountUser convert(UserService userService) {
        User user = userService.findById(userId);
        return new BankAccountUser(user, accountNumber, accountDigit, isMain);
    }

    public BankAccountUser update(Long id, BankAccountUserService bankAccountUserService) {
        BankAccountUser bankAccountUser = bankAccountUserService.findById(id);
        bankAccountUser.setAccountNumber(accountNumber);
        bankAccountUser.setAccountDigit(accountDigit);
        bankAccountUser.setIsMain(isMain);
        return bankAccountUser;
    }
}
