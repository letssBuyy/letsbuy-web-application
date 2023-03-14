package com.application.letsbuy.api.usecase;

public interface Sendable {
 public Boolean sendWelcome(String email, String name);
 public Boolean sendChangePassword(String email, String name);
 public Boolean sendSaleConfirmation(String email, String name);
}
