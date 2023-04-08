package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.exceptions.PasswordValidationException;
import com.application.letsbuy.internal.services.UserService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDto {

    private String name;
    private String email;
    private String cpf;
    private LocalDate birthDate;
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
        if (password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$")) {
        password = new BCryptPasswordEncoder().encode(getPassword());
        return new User(name, email, cpf, password, birthDate, phoneNumber);
        }
        throw  new PasswordValidationException();
    }
}
