package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.dto.PagSeguroDto;
import com.application.letsbuy.internal.dto.PaymentUserAdvertisementRequestDto;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.repositories.AdversimentRepository;
import com.application.letsbuy.internal.repositories.UserRepository;
import com.application.letsbuy.internal.utils.PagSeguroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@Slf4j
public class CreatePaymentServiceBroker {

    private final RestTemplate restTemplate;

    private final UserRepository userRepository;

    private final AdversimentRepository adversimentRepository;


    @Autowired
    public CreatePaymentServiceBroker(UserRepository userRepository, AdversimentRepository adversimentRepository, @Value("${pagseguro.endpoint}") String pagSeguroEndpoint) {
        this.userRepository = userRepository;
        this.adversimentRepository = adversimentRepository;
        this.restTemplate = new RestTemplateBuilder().rootUri(pagSeguroEndpoint).build();
    }

    public PagSeguroDto createTransaction(PaymentUserAdvertisementRequestDto paymentUserAdvertisementRequestDto) {

        Assert.notNull(paymentUserAdvertisementRequestDto.getIdUser(), "user cannot be null");
        Assert.notNull(paymentUserAdvertisementRequestDto.getIdAdvertisement(), "advertisement cannot be null");

        Optional<User> user = this.userRepository.findById(paymentUserAdvertisementRequestDto.getIdUser());
        Optional<Adversiment> adversiment = this.adversimentRepository.findById(paymentUserAdvertisementRequestDto.getIdAdvertisement());

        PagSeguroDto pagSeguroDto;

        if (user.isPresent() && adversiment.isPresent()) {

            pagSeguroDto = PagSeguroUtils.convertPaymentUserAdvertisementRequestDtoToPagSeguroDto(user.get(), adversiment.get(), paymentUserAdvertisementRequestDto);

            try {

                HttpHeaders headers = setHeaders();
                HttpEntity<PagSeguroDto> requestEntity = new HttpEntity<>(pagSeguroDto, headers);
                ResponseEntity<PagSeguroDto> result = restTemplate.exchange(
                        "/orders", HttpMethod.POST, requestEntity,
                        new ParameterizedTypeReference<>() {
                        });

                result.getStatusCode();
                log.info(
                        "[TRANSACTION-PAG-SEGURO-BROKER] Transaction creation for payment of the plan completed successfully");

                return result.getBody();
            }
            catch (Exception e) {
                log.error("[TRANSACTION-PAG-SEGURO-BROKER] There was an error creating transaction for plan payment", e);
                return null;
            }
        }

        throw new IllegalArgumentException();
    }

    /**
     * Define as configurações do cabeçalho da requisição HTTP.
     * @return O cabeçalho da requisição.
     */
    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("063CE6AA33624D9095E58F34FBFE5799");
        return headers;
    }

}
