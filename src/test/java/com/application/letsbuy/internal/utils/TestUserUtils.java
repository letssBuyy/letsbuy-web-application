package com.application.letsbuy.internal.utils;

import com.application.letsbuy.internal.entities.User;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class TestUserUtils {
    public User createUserUtils() {
        User user = new User();
        user.setName("Usuario de teste");
        user.setEmail("teste@gmail.com");
        user.setCpf("00223928828");
        user.setPassword("#GfTeste12345");
        user.setBirthDate(LocalDate.of(1999, 10, 1));
        user.setPhoneNumber("14999374281");
        return user;
    }
}
