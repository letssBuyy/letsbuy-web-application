package com.application.letsbuy.internal.utils;

import com.application.letsbuy.internal.dto.*;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.User;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.UUID;

@UtilityClass
public class PagSeguroUtils {


    public PagSeguroDto convertPaymentUserAdvertisementRequestDtoToPagSeguroDto(User user, Adversiment adversiment, PaymentUserAdvertisementRequestDto paymentUserAdvertisementRequestDto) {
        return createPagSeguroRequestDto(user, adversiment, paymentUserAdvertisementRequestDto);
    }

    private PagSeguroDto createPagSeguroRequestDto(User user, Adversiment adversiment, PaymentUserAdvertisementRequestDto paymentUserAdvertisementRequestDto) {
        PagSeguroDto pagSeguroDto = new PagSeguroDto();
        String referenceId = UUID.randomUUID().toString();
        pagSeguroDto.setReferenceId(referenceId);
        pagSeguroDto.setNotificationUrls(List.of("https://letsbuy-api-dev.azurewebsites.net/"));
        pagSeguroDto.setCustomer(createCustomerPagSeguroDto(user));
        pagSeguroDto.setItems(List.of(createItemsPagSeguroDto(referenceId, adversiment)));
        pagSeguroDto.setCharges(List.of(createChargesPagSeguroDto(referenceId, adversiment, paymentUserAdvertisementRequestDto)));

        if (Boolean.TRUE.equals(paymentUserAdvertisementRequestDto.getIsShipment())) {
            pagSeguroDto.setShipping(createShippingPagSeguroDto(user));
        }

        return pagSeguroDto;
    }

    public ShippingPagSeguroDto createShippingPagSeguroDto(User user) {
        ShippingPagSeguroDto shippingPagSeguroDto = new ShippingPagSeguroDto();
        shippingPagSeguroDto.setAddress(createAddressPagSeguroDto(user));
        return shippingPagSeguroDto;
    }

    private AddressPagSeguroDto createAddressPagSeguroDto(User user) {
        AddressPagSeguroDto addressPagSeguroDto = new AddressPagSeguroDto();
        addressPagSeguroDto.setCity(user.getCity());
        addressPagSeguroDto.setNumber(user.getNumber().toString());
        addressPagSeguroDto.setCountry("Brasil");
        addressPagSeguroDto.setStreet(user.getRoad());
        addressPagSeguroDto.setRegionCode("SP");
        addressPagSeguroDto.setLocality(user.getNeighborhood());
        addressPagSeguroDto.setComplement(user.getComplement());
        addressPagSeguroDto.setPostalCode(user.getCep());
        return addressPagSeguroDto;
    }

    private ItemsPagSeguroDto createItemsPagSeguroDto(String referenceId, Adversiment adversiment ) {
        ItemsPagSeguroDto itemsPagSeguroDto = new ItemsPagSeguroDto();
        itemsPagSeguroDto.setName(adversiment.getTitle());
        itemsPagSeguroDto.setReferenceId(referenceId);
        itemsPagSeguroDto.setQuantity(1);
        itemsPagSeguroDto.setUnitAmount(adversiment.getPrice().intValue());
        return itemsPagSeguroDto;
    }

    private CustomerPagSeguroDto createCustomerPagSeguroDto(User user) {
        CustomerPagSeguroDto customerPagSeguroDto = new CustomerPagSeguroDto();
        customerPagSeguroDto.setEmail(user.getEmail());
        customerPagSeguroDto.setName(user.getName());
        customerPagSeguroDto.setTaxId(user.getCpf());
        customerPagSeguroDto.setPhones(List.of(createPhonePagSeguroDto(user)));
        return customerPagSeguroDto;
    }

    private PhonePagSeguroDto createPhonePagSeguroDto(User user) {
        PhonePagSeguroDto phonePagSeguroDto = new PhonePagSeguroDto();
        phonePagSeguroDto.setArea("11");
        phonePagSeguroDto.setType("MOBILE");
        phonePagSeguroDto.setCountry("55");
        phonePagSeguroDto.setNumber(user.getPhoneNumber().substring(2));
        return phonePagSeguroDto;
    }

    private ChargesPagSeguroDto createChargesPagSeguroDto(String referenceId, Adversiment adversiment, PaymentUserAdvertisementRequestDto paymentUserAdvertisementRequestDto) {
        ChargesPagSeguroDto chargesPagSeguroDto = new ChargesPagSeguroDto();
        chargesPagSeguroDto.setAmount(createAmountDto(adversiment));
        chargesPagSeguroDto.setReferenceId(referenceId);
        chargesPagSeguroDto.setNotificationUrls(List.of("https://letsbuy-api-dev.azurewebsites.net/"));
        chargesPagSeguroDto.setDescription(adversiment.getTitle());
        chargesPagSeguroDto.setPaymentMethod(createPaymentMethod(paymentUserAdvertisementRequestDto));
        return chargesPagSeguroDto;
    }

    private PaymentMethodPagSeguroDto createPaymentMethod(PaymentUserAdvertisementRequestDto paymentUserAdvertisementRequestDto) {
        PaymentMethodPagSeguroDto paymentMethodPagSeguroDto = new PaymentMethodPagSeguroDto();
        paymentMethodPagSeguroDto.setType("CREDIT_CARD");
        paymentMethodPagSeguroDto.setInstallments(1);
        paymentMethodPagSeguroDto.setCapture(true);
        paymentMethodPagSeguroDto.setCard(createCard(paymentUserAdvertisementRequestDto));
        return paymentMethodPagSeguroDto;
    }

    private CardPagSeguroDto createCard(PaymentUserAdvertisementRequestDto paymentUserAdvertisementRequestDto) {
        CardPagSeguroDto cardPagSeguroDto = new CardPagSeguroDto();
        cardPagSeguroDto.setNumber(paymentUserAdvertisementRequestDto.getCardNumber());
        cardPagSeguroDto.setStore(true);
        cardPagSeguroDto.setExpMonth(paymentUserAdvertisementRequestDto.getExpMonth());
        cardPagSeguroDto.setExpYear(paymentUserAdvertisementRequestDto.getExpYear());
        cardPagSeguroDto.setSecurityCode(paymentUserAdvertisementRequestDto.getSecurityCode());
        cardPagSeguroDto.setHolder(createHolder(paymentUserAdvertisementRequestDto));
        return cardPagSeguroDto;
    }

    private HolderPagSeguroDto createHolder(PaymentUserAdvertisementRequestDto paymentUserAdvertisementRequestDto) {
        HolderPagSeguroDto holderPagSeguroDto = new HolderPagSeguroDto();
        holderPagSeguroDto.setName(paymentUserAdvertisementRequestDto.getHolderName());
        return holderPagSeguroDto;
    }

    private AmountPagSeguroDto createAmountDto(Adversiment adversiment) {
        AmountPagSeguroDto amountPagSeguroDto = new AmountPagSeguroDto();
        amountPagSeguroDto.setCurrency("BRL");
        amountPagSeguroDto.setValue(adversiment.getPrice().intValue());
        return amountPagSeguroDto;
    }

}
