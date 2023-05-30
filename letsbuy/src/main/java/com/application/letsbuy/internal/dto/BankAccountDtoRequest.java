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
    private String accountNumber;
    @NotBlank
    private String accountDigit;
    @NotNull
    private Boolean isMain;


    public BankAccount convert(UserService userService) {
        User user = userService.findById(userId);
        return new BankAccount(user, accountNumber, accountDigit, isMain);
    }

    public BankAccount update(Long id, BankAccountService bankAccountService) {
        BankAccount bankAccount = bankAccountService.findById(id);
        bankAccount.setAccountNumber(accountNumber);
        bankAccount.setAccountDigit(accountDigit);
        bankAccount.setIsMain(isMain);
        return bankAccount;
    }
}
