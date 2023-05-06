package com.application.letsbuy.internal.entities;

import com.application.letsbuy.internal.enums.AccountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BankAccountUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private AccountType accountType;

    private String accountNumber;

    private String accountDigit;

    private Boolean isMain;


    public BankAccountUser(Long id, User user, AccountType accountType, String accountNumber, String accountDigit, Boolean isMain) {
        this.id = id;
        this.user = user;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.accountDigit = accountDigit;
        this.isMain = isMain;
    }
}
