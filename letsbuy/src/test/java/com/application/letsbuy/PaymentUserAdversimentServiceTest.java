package com.application.letsbuy;


import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.PaymentUserAdvertisement;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.enums.AdversimentEnum;
import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.QualityEnum;
import com.application.letsbuy.internal.repositories.AdversimentRepository;
import com.application.letsbuy.internal.repositories.UserRepository;
import com.application.letsbuy.internal.services.PaymentUserAdversimentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class PaymentUserAdversimentServiceTest {

    @Autowired
    private PaymentUserAdversimentService paymentUserAdversimentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdversimentRepository adversimentRepository;

    @Test
    void execute() {
        User user = this.createUserBuyer();
        Adversiment adversiment = this.createAdversiment(user);
        PaymentUserAdvertisement paymentUserAdversiment = this.paymentUserAdversimentService.create(adversiment.getId(), user.getId());
        Assertions.assertNotNull(paymentUserAdversiment);
    }

    /**
     * {
     * 	"userId": 1,
     * 	"title": "Iphone",
     * 	"description": "Modelo 14",
     * 	"price": 50.00,
     * 	"postDate": "1997-05-06",
     * 	"lastUpdate": "2023-01-03",
     * 	"saleDate": "2023-05-06",
     * 	"category": "VEHICLES",
     * 	"quality": "NEW"
     * }
     */

    private User createUserBuyer() {
        User user = new User();
        user.setName("Gustavo");
        user.setPassword("gustavo@12345");
        user.setEmail("gustavo@gmail.com");
        user.setBirthDate(LocalDate.of(1999, 11, 23));
        user.setPhoneNumber("11965193497");
        user.setCpf("67544434567");
        return this.userRepository.save(user);
    }

    private Adversiment createAdversiment(User user) {
        Adversiment adversiment = new Adversiment();
        adversiment.setIsActive(AdversimentEnum.ACTIVE);
        adversiment.setUser(user);
        adversiment.setTitle("Iphone 11");
        adversiment.setDescription("blablabla");
        adversiment.setPrice(3500.00);
        adversiment.setPostDate(LocalDate.now());
        adversiment.setLastUpdate(LocalDate.now());
        adversiment.setCategory(CategoryEnum.FILMS);
        adversiment.setQuality(QualityEnum.NEW);
        return this.adversimentRepository.save(adversiment);
    }
}
