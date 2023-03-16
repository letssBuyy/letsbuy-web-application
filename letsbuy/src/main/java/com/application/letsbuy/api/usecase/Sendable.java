package com.application.letsbuy.api.usecase;

public interface Sendable {
        public Boolean sendWelcome(String receiverEmail, String receiverName);
        public Boolean sendChangePassword(String receiverEmail, String receiverName);
        public Boolean sendSaleConfirmation(String receiverEmail, String receiverName);
}
