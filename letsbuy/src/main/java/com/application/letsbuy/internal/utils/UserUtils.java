package com.application.letsbuy.internal.utils;

import com.application.letsbuy.internal.entities.User;

import java.time.LocalDate;

public final class UserUtils {
    private UserUtils() {}

    public static User mockUser() {
        User user = new User();
        user.setId(10L);
        user.setName("Marcos Leonardo");
        user.setEmail("marcos.leonardo@gmail.com");
        user.setCpf("75997065006");
        user.setPassword("#GfMarcos12345");
        user.setBirthDate(LocalDate.of(1999, 10, 01));
        user.setPhoneNumber("11970300986");
        return user;
    }

}
