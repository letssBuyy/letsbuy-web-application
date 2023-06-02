package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.dto.TransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class CreatePaymentServiceBroker {

    private final RestTemplate restTemplate;

    @Value("${mercado.pago.url}")
    private String endpointMercadoPago;

    @Autowired
    public CreatePaymentServiceBroker() {
        this.restTemplate = new RestTemplateBuilder().rootUri(endpointMercadoPago).build();
    }


    public TransactionDto createTransaction(Long idUser, Long idAdvertisement) {

        Assert.notNull(idUser, "userId cannot be null");
        Assert.notNull(idAdvertisement, "idAdvertisement cannot be null");


        TransactionDto transactionDto = new TransactionDto();

        try {
            HttpHeaders headers = setHeaders();
            HttpEntity<TransactionDto> requestEntity = new HttpEntity<>(transactionDto, headers);
            ResponseEntity<TransactionDto> result = restTemplate.exchange(
                    "/pagar/" + idUser + "/" + idAdvertisement, HttpMethod.POST, requestEntity,
                    new ParameterizedTypeReference<>() {
                    });
            result.getStatusCode();
            log.info(
                    "[TRANSACTION-MERCADO-PAGO-BROKER] Transaction creation for payment of the plan completed successfully");
            return result.getBody();
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
