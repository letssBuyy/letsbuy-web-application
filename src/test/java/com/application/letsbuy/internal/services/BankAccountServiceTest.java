package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.dto.BankAccountDtoRequest;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.BankAccount;
import com.application.letsbuy.internal.exceptions.BankAccountNotFoundException;
import com.application.letsbuy.internal.utils.TestAdversimentUtils;
import com.application.letsbuy.internal.utils.TestBankAccountUtils;
import com.application.letsbuy.internal.utils.TestUserUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class BankAccountServiceTest {

    @Autowired
    private AdversimentService adversimentService;

    @Autowired
    private UserService userService;

    @Autowired
    private BankAccountService bankAccountService;

    @Test
    @DirtiesContext
    void saveBankAccount() {
        Adversiment adversiment = TestAdversimentUtils.mockAdversiment();
        this.userService.save(TestUserUtils.createUserUtils());
        adversiment.setUser(this.userService.findByEmail(TestUserUtils.createUserUtils().getEmail()));
        this.adversimentService.save(adversiment);
        BankAccount bankAccount = TestBankAccountUtils.createBankAccountUtils();
        bankAccount.setUser(this.userService.findByEmail(TestUserUtils.createUserUtils().getEmail()));
        Assertions.assertDoesNotThrow(() -> this.bankAccountService.saveBankAccount(bankAccount));
    }

    @Test
    @DirtiesContext
    void update() {
        Adversiment adversiment = TestAdversimentUtils.mockAdversiment();
        this.userService.save(TestUserUtils.createUserUtils());
        adversiment.setUser(this.userService.findByEmail(TestUserUtils.createUserUtils().getEmail()));
        this.adversimentService.save(adversiment);
        BankAccount bankAccount = TestBankAccountUtils.createBankAccountUtils();
        bankAccount.setUser(this.userService.findByEmail(TestUserUtils.createUserUtils().getEmail()));
        Assertions.assertDoesNotThrow(() -> this.bankAccountService.saveBankAccount(bankAccount));
        BankAccountDtoRequest bankAccountDtoRequest = new BankAccountDtoRequest();
        bankAccountDtoRequest.setBankNumber("123");
        bankAccountDtoRequest.setAccountNumber("2345");
        bankAccountDtoRequest.setAgencyNumber("12121");
        bankAccountDtoRequest.setUserId(1L);
        this.bankAccountService.update(1L, bankAccountDtoRequest);
        Assertions.assertEquals("123", this.bankAccountService.findById(1L).getBankNumber());
    }

    @Test
    @DirtiesContext
    void deleteById() {
        Adversiment adversiment = TestAdversimentUtils.mockAdversiment();
        this.userService.save(TestUserUtils.createUserUtils());
        adversiment.setUser(this.userService.findByEmail(TestUserUtils.createUserUtils().getEmail()));
        this.adversimentService.save(adversiment);
        BankAccount bankAccount = TestBankAccountUtils.createBankAccountUtils();
        bankAccount.setUser(this.userService.findByEmail(TestUserUtils.createUserUtils().getEmail()));
        Assertions.assertDoesNotThrow(() -> this.bankAccountService.saveBankAccount(bankAccount));
        this.bankAccountService.deleteById(1L);
       Assertions.assertThrows(BankAccountNotFoundException.class, () -> this.bankAccountService.findById(1L));
    }
}