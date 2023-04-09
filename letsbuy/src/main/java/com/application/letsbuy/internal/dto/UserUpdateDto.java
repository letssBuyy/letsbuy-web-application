package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

    private String name;
    private String email;
    private String cpf;
    private LocalDate birthDate;
    private String phoneNumber;

    public User update(Long id, UserService userService) {
        User user = userService.findById(id);
        user.setName(name);
        user.setEmail(email);
        user.setCpf(cpf);
        user.setBirthDate(birthDate);
        user.setPhoneNumber(phoneNumber);
        return user;
    }
}
