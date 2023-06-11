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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String bankNumber;

    @Column
    private String agencyNumber;

    @Column
    private String accountNumber;

    public BankAccount(User user, String bankNumber, String agencyNumber, String accountNumber) {
        this.user = user;
        this.bankNumber = bankNumber;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
    }
}
