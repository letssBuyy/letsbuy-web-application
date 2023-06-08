package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.dto.MercadoPagoRequestDto;
import com.application.letsbuy.internal.dto.TransactionDto;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
public class CreatePaymentServiceBroker {

    private final RestTemplate restTemplate;

    private String endpointMercadoPago;

    @Autowired
    public CreatePaymentServiceBroker(@Value("${mercado.api}") String endpointMercadoPago) {
        this.restTemplate = new RestTemplateBuilder().rootUri(endpointMercadoPago).build();
    }


    public TransactionDto createTransaction(User user, Adversiment adversiment) {

        Assert.notNull(user, "user cannot be null");
        Assert.notNull(adversiment, "advertisement cannot be null");

        // monta a request para criar o pagamento no mercado pago
        MercadoPagoRequestDto request = new MercadoPagoRequestDto();
        request.setName(user.getName());
        request.setIdUser(user.getId());
        request.setEmail(user.getEmail());
        request.setDescriptionAdvertisement(adversiment.getDescription());
        request.setValor(adversiment.getPrice());

        try {
            HttpHeaders headers = setHeaders();
            HttpEntity<MercadoPagoRequestDto> requestEntity = new HttpEntity<>(request, headers);
            ResponseEntity<List<TransactionDto>> result = restTemplate.exchange(
                    "/criar-pagamento", HttpMethod.POST, requestEntity,
                    new ParameterizedTypeReference<>() {
                    });

            result.getStatusCode();
            log.info(
                    "[TRANSACTION-MERCADO-PAGO-BROKER] Transaction creation for payment of the plan completed successfully");

            return result.getBody().get(0);
        }
        catch (Exception e) {
            log.error("[TRANSACTION-MERCADO-PAGO-BROKER] There was an error creating transaction for plan payment", e);
            return null;
        }
    }

    /**
     * Define as configurações do cabeçalho da requisição HTTP.
     * @return O cabeçalho da requisição.
     */
    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
