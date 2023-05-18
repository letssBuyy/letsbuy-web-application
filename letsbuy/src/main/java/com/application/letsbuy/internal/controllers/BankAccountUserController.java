package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.AdversimentDtoResponse;
import com.application.letsbuy.internal.dto.BankAccountUserDtoRequest;
import com.application.letsbuy.internal.dto.BankAccountUserDtoResponse;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.BankAccountUser;
import com.application.letsbuy.internal.services.BankAccountUserService;
import com.application.letsbuy.internal.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequestMapping("/bank-account-users")
public class BankAccountUserController {

    private BankAccountUserService bankAccountUserService;
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<BankAccountUserDtoResponse>> listAccounts() {
        List<BankAccountUser> accounts = bankAccountUserService.list();

        if (accounts.isEmpty()) {
            return noContent().build();
        }
        return ResponseEntity.ok().body(BankAccountUserDtoResponse.convert(accounts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountUserDtoResponse> findAccount(@PathVariable Long id) {
        BankAccountUser bankAccountUser = bankAccountUserService.findById(id);
        return ResponseEntity.ok().body(new BankAccountUserDtoResponse(bankAccountUser));
    }

    @PostMapping
    public ResponseEntity<BankAccountUserDtoResponse> saveAccount(@RequestBody BankAccountUserDtoRequest bankAccountUserDtoRequest) {
        BankAccountUser bankAccountUser = bankAccountUserDtoRequest.convert(userService);
        bankAccountUserService.saveBankAccount(bankAccountUser);
        return ResponseEntity.created(null).body(new BankAccountUserDtoResponse(bankAccountUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountUserDtoResponse> updateAccount(@PathVariable Long id, @RequestBody BankAccountUserDtoRequest bankAccountUserDtoRequest) {
        BankAccountUser bankAccountUser = bankAccountUserDtoRequest.update(id, bankAccountUserService);
        return ResponseEntity.ok().body(new BankAccountUserDtoResponse(bankAccountUser));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        bankAccountUserService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
