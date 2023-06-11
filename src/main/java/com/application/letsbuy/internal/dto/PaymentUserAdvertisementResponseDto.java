package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Payment;
import com.application.letsbuy.internal.entities.PaymentUserAdvertisement;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class PaymentUserAdvertisementResponseDto {

    private Long idUser;

    private Long idAdvertisement;

    private Payment payment;

    private LocalDateTime receivableDate;

    public static PaymentUserAdvertisementResponseDto parseEntityToDto(PaymentUserAdvertisement paymentUserAdvertisement) {
        PaymentUserAdvertisementResponseDto responseDto = new PaymentUserAdvertisementResponseDto();
        responseDto.setIdAdvertisement(paymentUserAdvertisement.getAdversiment().getId());
        responseDto.setPayment(paymentUserAdvertisement.getPayment());
        responseDto.setIdUser(paymentUserAdvertisement.getBuyer().getId());
        responseDto.setReceivableDate(paymentUserAdvertisement.getReceivableDate());
        return responseDto;
    }
}
