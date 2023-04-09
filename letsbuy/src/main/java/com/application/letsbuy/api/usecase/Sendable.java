package com.application.letsbuy.api.usecase;

public interface Sendable {
        Boolean sendWelcome(String receiverEmail, String receiverName);
        Boolean sendChangePassword(String receiverEmail, String receiverName);
        Boolean sendSaleConfirmation(String receiverEmail, String receiverName);
}
