package com.application.letsbuy.internal.utils;

import com.application.letsbuy.internal.entities.BankAccount;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestBankAccountUtils {

    public BankAccount createBankAccountUtils() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber("2345");
        bankAccount.setBankNumber("12345");
        bankAccount.setAgencyNumber("12121");
        return bankAccount;
    }
    }
