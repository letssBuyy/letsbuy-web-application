package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.services.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private String nome;

    private String email;

    private String cpf;

    private String birthDate;

    private String phoneNumber;

    private String password;

    public UserDto(User user) {
        this.nome = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.password = user.getPassword();
        this.birthDate = user.getBirthDate();
        this.phoneNumber = user.getPhoneNumber();
    }

    public User convert() {
        return new User(nome, email, cpf, password, birthDate, phoneNumber);
    }

    public User update(Long id, UserService userService) {
        User user = userService.findById(id);
        user.setName(user.getName());
        user.setEmail(email);
        user.setCpf(user.getCpf());
        user.setPassword(user.getPassword());
        user.setBirthDate(user.getBirthDate());
        user.setPhoneNumber(phoneNumber);
        return user;
    }
}
