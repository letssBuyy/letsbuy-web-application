package com.application.letsbuy;


import com.application.letsbuy.internal.dto.TransactionDto;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.Payment;
import com.application.letsbuy.internal.entities.PaymentUserAdvertisement;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.PaymentStatusEnum;
import com.application.letsbuy.internal.enums.QualityEnum;
import com.application.letsbuy.internal.services.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PaymentUserAdvertisementServiceTest {

    @Mock
    private PaymentUserAdversimentService paymentUserAdversimentService;

    @Mock
    private CreatePaymentServiceBroker createPaymentServiceBroker;

    @Mock
    private UserService userService;

    @Mock
    private AdversimentService adversimentService;

    @Mock
    private PaymentService paymentService;


    @Test
    @Disabled
    void execute() {

        Mockito.when(createPaymentServiceBroker.createTransaction(Mockito.any(), Mockito.any())).thenReturn(this.createTransactionDto());
        Mockito.when(userService.findById(Mockito.any())).thenReturn(this.createUser());
        Mockito.when(adversimentService.findById(Mockito.any())).thenReturn(this.createAdvertisement());
        Mockito.when(paymentService.retrieveByExternalReference(Mockito.any())).thenReturn(Optional.of(this.createPayment()));

        PaymentUserAdvertisement paymentUserAdvertisement = this.paymentUserAdversimentService.create(1L, 1L);
        verify(createPaymentServiceBroker).createTransaction(Mockito.any(), Mockito.any());
    }

    private TransactionDto createTransactionDto() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(5000.00);
        transactionDto.setCupomUser("leo");
        transactionDto.setStatus("WAITING");
        transactionDto.setDescription("Pagamento anuncio");
        transactionDto.setExternalReference("1234567Pagamento");
        transactionDto.setInitPoint("http://localhost:8081/pagar");
        return transactionDto;
    }

    private User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Gustavo Rezende");
        user.setPhoneNumber("11945678908");
        user.setCpf("18040567076");
        user.setEmail("user@gmail.com");
        user.setBirthDate(LocalDate.now());
        user.setPassword("@Usuario123$");
        user.setAdversiments(new ArrayList<>());
        return user;
    }

    private Adversiment createAdvertisement() {
        Adversiment adversiment = new Adversiment();
        adversiment.setId(1L);
        adversiment.setUser(new User());
        adversiment.setTitle("Iphone");
        adversiment.setDescription("Modelo 14");
        adversiment.setPrice(5000.00);
        adversiment.setPostDate(LocalDate.now());
        adversiment.setCategory(CategoryEnum.ELECTRONICS);
        adversiment.setQuality(QualityEnum.NEW);
        return adversiment;
    }

    private Payment createPayment() {
        Payment payment = new Payment();
        payment.setAmount(new BigDecimal("5000.00").setScale(2, BigDecimal.ROUND_HALF_DOWN));
        payment.setDescription("Modelo 14");
        payment.setExternalReference("1234567Pagamento");
        payment.setInitPoint("http://localhost:8081/pagar");
        payment.setStatus(PaymentStatusEnum.WAITING);
        payment.setCurrencyId("BRL");
        payment.setCupomUser("leo");
        payment.setId(1L);
        payment.setCreatedAt(LocalDateTime.now());
        return payment;
    }
}
