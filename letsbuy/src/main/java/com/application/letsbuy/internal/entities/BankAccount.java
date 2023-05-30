package com.application.letsbuy.internal.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String accountNumber;
    private String accountDigit;
    private Boolean isMain;

    public BankAccount(User user, String accountNumber, String accountDigit, Boolean isMain) {
        this.user = user;
        this.accountNumber = accountNumber;
        this.accountDigit = accountDigit;
        this.isMain = isMain;
    }
}
