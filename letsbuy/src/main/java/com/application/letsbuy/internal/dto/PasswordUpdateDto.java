package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.services.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
public class PasswordUpdateDto {

    private String password;

    public User updatePassword(Long id, UserService userService) {
        User user = userService.findById(id);
        user.setPassword(new BCryptPasswordEncoder().encode(getPassword()));
        return user;
    }
}
