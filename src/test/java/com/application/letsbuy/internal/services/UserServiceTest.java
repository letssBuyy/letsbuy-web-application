package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.enums.ActiveInactiveEnum;
import com.application.letsbuy.internal.exceptions.UserConflictException;
import com.application.letsbuy.internal.utils.TestUserUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @DirtiesContext
    void saveUserTest() {
        User user = TestUserUtils.createUserUtils();
        this.userService.save(user);
        Assertions.assertEquals("teste@gmail.com", this.userService.findById(1L).getEmail());
    }

    @Test
    @DirtiesContext
    void saveUserConflictTest() {
        User user = TestUserUtils.createUserUtils();
        this.userService.save(user);
        Assertions.assertThrows(UserConflictException.class, () -> this.userService.save(user));
    }

    @Test
    @DirtiesContext
    void findByName() {
        User user = TestUserUtils.createUserUtils();
        this.userService.save(user);
        Assertions.assertEquals(user.getCpf(), this.userService.findByName(user.getName()).getCpf());
    }

    @Test
    @DirtiesContext
    void quantityUsers() {
        User user = TestUserUtils.createUserUtils();
        this.userService.save(user);
        Assertions.assertEquals(1L, this.userService.quantityUsers());
    }

    @Test
    @DirtiesContext
    void findByEmail() {
        User user = TestUserUtils.createUserUtils();
        this.userService.save(user);
        Assertions.assertEquals(user.getCpf(), this.userService.findByEmail(user.getEmail()).getCpf());
    }

    @Test
    @DirtiesContext
    void deleteById() {
        User user = TestUserUtils.createUserUtils();
        this.userService.save(user);
        this.userService.deleteById(this.userService.findByEmail(user.getEmail()).getId());
        Assertions.assertEquals(ActiveInactiveEnum.INACTIVE, this.userService.findByEmail(user.getEmail()).getIsActive());
    }
}