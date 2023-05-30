package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.services.UserService;
import com.application.letsbuy.internal.utils.AgeRange;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    @Email
    @NotBlank
    private String email;
    @CPF
    @NotBlank
    private String cpf;
    @AgeRange(minAge = 18)
    private LocalDate birthDate;
    @Pattern(
            regexp = "^(?:\\+55\\s?)?(?:\\([1-9][1-9]\\)|[1-9][1-9])\\s?(?:9?[1-9]\\d{3})[-\\s]?\\d{4}$",
            message = "Numero de celular inválido!"
    )
    private String phoneNumber;
    private String cep;
    private String road;
    private Long number;
    private String neighborhood;
    private String complement;
    private String state;
    private String city;


    public User update(Long id, UserService userService) {
        User user = userService.findById(id);
        user.setName(name);
        user.setEmail(email);
        user.setCpf(cpf);
        user.setBirthDate(birthDate);
        user.setPhoneNumber(phoneNumber);
        user.setCep(cep);
        user.setRoad(road);
        user.setNumber(number);
        user.setNeighborhood(neighborhood);
        user.setComplement(complement);
        user.setState(state);
        user.setCity(city);

        return user;
    }
}
