package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.utils.TestAdversimentUtils;
import com.application.letsbuy.internal.utils.TestUserUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;


@SpringBootTest
@Transactional
@ActiveProfiles("test")
class CreatePaymentServiceBrokerTest {

    @Autowired
    private AdversimentService adversimentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CreatePaymentServiceBroker createPaymentServiceBroker;

    @Test
    @DirtiesContext
    void createTransactionTest() {
        Adversiment adversiment = TestAdversimentUtils.mockAdversiment();
        this.userService.save(TestUserUtils.createUserUtils());
        adversiment.setUser(this.userService.findByEmail(TestUserUtils.createUserUtils().getEmail()));
        this.adversimentService.save(adversiment);
        Assertions.assertDoesNotThrow(() -> this.createPaymentServiceBroker.createTransaction(this.userService.findByEmail(TestUserUtils.createUserUtils().getEmail()),
                this.adversimentService.findById(1L)));
    }
}