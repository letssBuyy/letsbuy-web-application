package com.application.letsbuy.internal.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String name;
    private String email;
    private String cpf;
     private String password;
    private String birthDate;
    private String phoneNumber;
    public User(String name, String email, String cpf, String password, String birthDate, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }
}
