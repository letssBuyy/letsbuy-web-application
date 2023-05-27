package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.entities.BankAccountUser;
import com.application.letsbuy.internal.exceptions.BankAccountNotFoundException;
import com.application.letsbuy.internal.repositories.BankAccountUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountUserService {

    private BankAccountUserRepository bankAccountUserRepository;

    public List<BankAccountUser> list() {
        return bankAccountUserRepository.findAll();
    }

    public void saveBankAccount(BankAccountUser bankAccountUser) {
        bankAccountUserRepository.save(bankAccountUser);
    }

    public BankAccountUser findById(Long id) {
        Optional<BankAccountUser> retrieveAccountById = bankAccountUserRepository.findById(id);
        if (retrieveAccountById.isPresent()) {
            return retrieveAccountById.get();
        } else {
            throw new BankAccountNotFoundException();
        }
    }

    public void deleteById(Long id) {
        if (bankAccountUserRepository.existsById(id)) {
            bankAccountUserRepository.deleteById(id);
        } else {
            throw new BankAccountNotFoundException();
        }

    }
}
