package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.entities.BankAccount;
import com.application.letsbuy.internal.exceptions.BankAccountNotFoundException;
import com.application.letsbuy.internal.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankAccountService {

    private BankAccountRepository bankAccountRepository;

    public List<BankAccount> list() {
        return bankAccountRepository.findAll();
    }

    public void saveBankAccount(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    public BankAccount findById(Long id) {
        Optional<BankAccount> retrieveAccountById = bankAccountRepository.findById(id);
        if (retrieveAccountById.isPresent()) {
            return retrieveAccountById.get();
        } else {
            throw new BankAccountNotFoundException();
        }
    }

    public void deleteById(Long id) {
        if (bankAccountRepository.existsById(id)) {
            bankAccountRepository.deleteById(id);
        } else {
            throw new BankAccountNotFoundException();
        }

    }
}
