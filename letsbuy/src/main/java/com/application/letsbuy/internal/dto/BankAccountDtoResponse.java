package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.BankAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class BankAccountDtoResponse {

    private Long userId;
    private Long id;
    private String accountNumber;
    private String accountDigit;
    private Boolean isMain;

    public static List<BankAccountDtoResponse> convert(List<BankAccount> bankAccounts) {
        return bankAccounts.stream().map(BankAccountDtoResponse::new).collect(Collectors.toList());
    }

    public BankAccountDtoResponse(BankAccount bankAccount) {
        this.userId = bankAccount.getUser().getId();
        this.id = bankAccount.getId();
        this.accountNumber = bankAccount.getAccountNumber();
        this.accountDigit = bankAccount.getAccountDigit();
        this.isMain = bankAccount.getIsMain();
    }
}
