package com.application.letsbuy.internal.services;

import com.application.letsbuy.api.usecase.Sendable;

public class EmailOutlookService implements Sendable {
    @Override
    public Boolean sendWelcome(String email, String name) {
        return null;
    }

    @Override
    public Boolean sendChangePassword(String email, String name) {
        return null;
    }

    @Override
    public Boolean sendSaleConfirmation(String email, String name) {
        return null;
    }
}
