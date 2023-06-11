package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.enums.AdversimentEnum;
import com.application.letsbuy.internal.utils.TestAdversimentUtils;
import com.application.letsbuy.internal.utils.TestUserUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class AdversimentServiceTest {

    @Autowired
    private AdversimentService adversimentService;

    @Autowired
    private UserService userService;

    @Test
    @DirtiesContext
    void saveAdversimentTest() {
        Adversiment adversiment = TestAdversimentUtils.mockAdversiment();
        this.userService.save(TestUserUtils.createUserUtils());
        adversiment.setUser(this.userService.findByEmail(TestUserUtils.createUserUtils().getEmail()));
        Assertions.assertDoesNotThrow(() -> this.adversimentService.save(adversiment));
        Assertions.assertNotNull(this.adversimentService.findById(1L));
    }

    @Test
    @DirtiesContext
    void deleteAdversimentTest() {
        Adversiment adversiment = TestAdversimentUtils.mockAdversiment();
        this.userService.save(TestUserUtils.createUserUtils());
        adversiment.setUser(this.userService.findByEmail(TestUserUtils.createUserUtils().getEmail()));
        Assertions.assertDoesNotThrow(() -> this.adversimentService.save(adversiment));
        this.adversimentService.deleteById(1L);
        Assertions.assertEquals(AdversimentEnum.INACTIVE, this.adversimentService.findById(1L).getIsActive());
    }

    @Test
    @DirtiesContext
    void findAllAdversimentsTest() {
        Adversiment adversiment = TestAdversimentUtils.mockAdversiment();
        this.userService.save(TestUserUtils.createUserUtils());
        adversiment.setUser(this.userService.findByEmail(TestUserUtils.createUserUtils().getEmail()));
        Assertions.assertDoesNotThrow(() -> this.adversimentService.save(adversiment));
        Assertions.assertEquals(1, this.adversimentService.findAll().size());
    }

    @Test
    @DirtiesContext
    void quantityAdversimentTest() {
        Adversiment adversiment = TestAdversimentUtils.mockAdversiment();
        this.userService.save(TestUserUtils.createUserUtils());
        adversiment.setUser(this.userService.findByEmail(TestUserUtils.createUserUtils().getEmail()));
        Assertions.assertDoesNotThrow(() -> this.adversimentService.save(adversiment));
        Assertions.assertEquals(1L, this.adversimentService.quantityAds());
    }

    @Test
    @DirtiesContext
    void countFinishedAdversimentsTest() {
        Adversiment adversiment = TestAdversimentUtils.mockAdversiment();
        this.userService.save(TestUserUtils.createUserUtils());
        adversiment.setIsActive(AdversimentEnum.SALLED);
        adversiment.setUser(this.userService.findByEmail(TestUserUtils.createUserUtils().getEmail()));
        Assertions.assertDoesNotThrow(() -> this.adversimentService.save(adversiment));
        Assertions.assertEquals(AdversimentEnum.SALLED, this.adversimentService.findById(1L).getIsActive());
    }

    @Test
    @DirtiesContext
    void likeAdversimentTest() {
        Adversiment adversiment = TestAdversimentUtils.mockAdversiment();
        this.userService.save(TestUserUtils.createUserUtils());
        adversiment.setUser(this.userService.findByEmail(TestUserUtils.createUserUtils().getEmail()));
        Assertions.assertDoesNotThrow(() -> this.adversimentService.save(adversiment));
        this.adversimentService.likeAdversiment(1L, 1L);
        Assertions.assertEquals(1, this.adversimentService.findAllAdversimentsLike().size());
    }


    @Test
    @DirtiesContext
    void findByAdversimentsLikeTest() {
        Adversiment adversiment = TestAdversimentUtils.mockAdversiment();
        this.userService.save(TestUserUtils.createUserUtils());
        adversiment.setUser(this.userService.findByEmail(TestUserUtils.createUserUtils().getEmail()));
        Assertions.assertDoesNotThrow(() -> this.adversimentService.save(adversiment));
        this.adversimentService.likeAdversiment(1L, 1L);
        Assertions.assertNotNull(this.adversimentService.findByAdversimentsLike(1L));
    }

    @Test
    @DirtiesContext
    void deslikeAdversimentTest() {
        Adversiment adversiment = TestAdversimentUtils.mockAdversiment();
        this.userService.save(TestUserUtils.createUserUtils());
        adversiment.setUser(this.userService.findByEmail(TestUserUtils.createUserUtils().getEmail()));
        Assertions.assertDoesNotThrow(() -> this.adversimentService.save(adversiment));
        this.adversimentService.likeAdversiment(1L, 1L);
        this.adversimentService.deslike(1L);
        Assertions.assertEquals(0, this.adversimentService.findByAdversimentsLike(1L).size());
    }
}