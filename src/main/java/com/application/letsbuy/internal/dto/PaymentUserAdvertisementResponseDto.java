package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Payment;
import com.application.letsbuy.internal.entities.PaymentUserAdvertisement;
import com.application.letsbuy.internal.utils.PagSeguroUtils;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class PaymentUserAdvertisementResponseDto {

    private UserDtoResponse user;

    private Boolean isShipment;

    private AdversimentDtoResponse adversiment;

    private Payment payment;

    private LocalDateTime receivableDate;

    public static List<PaymentUserAdvertisementResponseDto> parseListEntityToListDto(List<PaymentUserAdvertisement> paymentUserAdvertisements) {
        return paymentUserAdvertisements.stream().map(PaymentUserAdvertisementResponseDto::parseEntityToDto).toList();
    }

    public static PaymentUserAdvertisementResponseDto parseEntityToDto(PaymentUserAdvertisement paymentUserAdvertisement) {
        PaymentUserAdvertisementResponseDto responseDto = new PaymentUserAdvertisementResponseDto();
        responseDto.setAdversiment(new AdversimentDtoResponse(paymentUserAdvertisement.getAdversiment()));
        responseDto.setPayment(paymentUserAdvertisement.getPayment());
        responseDto.setUser(new UserDtoResponse(paymentUserAdvertisement.getBuyer()));
        responseDto.setReceivableDate(paymentUserAdvertisement.getReceivableDate());
        responseDto.setIsShipment(paymentUserAdvertisement.getIsShipment());
        return responseDto;
    }
}
