package com.application.letsbuy.internal.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String accountNumber;

    @Column
    private String accountDigit;

    public BankAccount(User user, String accountNumber, String accountDigit) {
        this.user = user;
        this.accountNumber = accountNumber;
        this.accountDigit = accountDigit;
    }
}
