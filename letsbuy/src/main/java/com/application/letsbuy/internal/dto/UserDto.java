package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.services.UserService;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class UserDto {

    private String name;

    private String email;

    private String cpf;

    private String birthDate;

    private String phoneNumber;

    private String password;

    public UserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.password = user.getPassword();
        this.birthDate = user.getBirthDate();
        this.phoneNumber = user.getPhoneNumber();
    }

    public User convert() {
        return new User(name, email, cpf, password, birthDate, phoneNumber);
    }

    public User update(Long id, UserService userService) {
        User user = userService.findById(id);
        user.setName(name);
        user.setEmail(email);
        user.setCpf(cpf);
        user.setPassword(password);
        user.setBirthDate(birthDate);
        user.setPhoneNumber(phoneNumber);
        return user;
    }
}
