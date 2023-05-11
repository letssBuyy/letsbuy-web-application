package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.BankAccountUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class BankAccountUserDtoResponse {

    private Long userId;
    private Long id;
    private String accountNumber;
    private String accountDigit;
    private Boolean isMain;

    public static List<BankAccountUserDtoResponse> convert(List<BankAccountUser> bankAccountUsers) {
        return bankAccountUsers.stream().map(BankAccountUserDtoResponse::new).collect(Collectors.toList());
    }

    public BankAccountUserDtoResponse(BankAccountUser bankAccountUser) {
        this.userId = bankAccountUser.getUser().getId();
        this.id = bankAccountUser.getId();
        this.accountNumber = bankAccountUser.getAccountNumber();
        this.accountDigit = bankAccountUser.getAccountDigit();
        this.isMain = bankAccountUser.getIsMain();
    }
}
