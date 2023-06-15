package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.exceptions.PasswordValidationException;
import com.application.letsbuy.internal.utils.AgeRange;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDto {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    @Email
    @NotBlank
    private String email;
    @CPF
    @NotBlank
    private String cpf;
    @NotBlank
    private String password;
    @AgeRange(minAge = 18)
    private LocalDate birthDate;
    @Pattern(
            regexp = "^(?:\\+55\\s?)?(?:\\([1-9][1-9]\\)|[1-9][1-9])\\s?(?:9?[1-9]\\d{3})[-\\s]?\\d{4}$",
            message = "Numero de celular inválido!"
    )
    private String phoneNumber;

    public UserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.password = user.getPassword();
        this.birthDate = user.getBirthDate();
        this.phoneNumber = user.getPhoneNumber();
    }

    public User convert() {
        password = new BCryptPasswordEncoder().encode(getPassword());
        return new User(name, email, cpf, password, birthDate, phoneNumber);
    }
}
