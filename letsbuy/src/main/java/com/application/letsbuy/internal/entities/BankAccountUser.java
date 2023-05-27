package com.application.letsbuy.internal.entities;

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
    @JoinColumn(name = "user_id")
    private User user;
    private String accountNumber;
    private String accountDigit;
    private Boolean isMain;

    public BankAccountUser(User user, String accountNumber, String accountDigit, Boolean isMain) {
        this.user = user;
        this.accountNumber = accountNumber;
        this.accountDigit = accountDigit;
        this.isMain = isMain;
    }

    @Override
    public String toString() {
        return "BankAccountUser{" +
                "id=" + id +
                ", user=" + user +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountDigit='" + accountDigit + '\'' +
                ", isMain=" + isMain +
                '}';
    }
}
