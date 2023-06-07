package com.application.letsbuy.api.usecase;

public interface Sendable {
        Boolean sendChangePassword(String receiverEmail, String receiverName, String receiverId);
}
