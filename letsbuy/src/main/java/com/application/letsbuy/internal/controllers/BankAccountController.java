package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.BankAccountDtoRequest;
import com.application.letsbuy.internal.dto.BankAccountDtoResponse;
import com.application.letsbuy.internal.entities.BankAccount;
import com.application.letsbuy.internal.services.BankAccountService;
import com.application.letsbuy.internal.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;

@AllArgsConstructor
@RestController
@RequestMapping("/bank-account-users")
public class BankAccountController {

    private BankAccountService bankAccountService;
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<BankAccountDtoResponse>> listAccounts() {
        List<BankAccount> accounts = bankAccountService.list();

        if (accounts.isEmpty()) {
            return noContent().build();
        }
        return ResponseEntity.ok().body(BankAccountDtoResponse.convert(accounts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountDtoResponse> findAccount(@PathVariable Long id) {
        BankAccount bankAccount = bankAccountService.findById(id);
        return ResponseEntity.ok().body(new BankAccountDtoResponse(bankAccount));
    }

    @PostMapping
    public ResponseEntity<BankAccountDtoResponse> saveAccount(@RequestBody @Valid BankAccountDtoRequest bankAccountDtoRequest) {
        System.out.println(bankAccountDtoRequest);
        BankAccount bankAccount = bankAccountDtoRequest.convert(userService);
        System.out.println(bankAccount);
        bankAccountService.saveBankAccount(bankAccount);
        return ResponseEntity.created(null).body(new BankAccountDtoResponse(bankAccount));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountDtoResponse> updateAccount(@PathVariable Long id, @RequestBody @Valid BankAccountDtoRequest bankAccountDtoRequest) {
        BankAccount bankAccount = bankAccountDtoRequest.update(id, bankAccountService);
        return ResponseEntity.ok().body(new BankAccountDtoResponse(bankAccount));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        bankAccountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
