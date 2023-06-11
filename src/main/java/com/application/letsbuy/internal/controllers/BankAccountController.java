package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.BankAccountDtoRequest;
import com.application.letsbuy.internal.dto.BankAccountDtoResponse;
import com.application.letsbuy.internal.entities.BankAccount;
import com.application.letsbuy.internal.services.BankAccountService;
import com.application.letsbuy.internal.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/bank-account-users")
public class BankAccountController {

    private BankAccountService bankAccountService;

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<BankAccountDtoResponse>> listAccounts() {
        List<BankAccount> accounts = this.bankAccountService.list();
        if (accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(BankAccountDtoResponse.convert(accounts), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountDtoResponse> findAccount(@PathVariable Long id) {
        return new ResponseEntity<>(new BankAccountDtoResponse(this.bankAccountService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BankAccountDtoResponse> saveAccount(@RequestBody @Valid BankAccountDtoRequest bankAccountDtoRequest) {
        BankAccount bankAccount = bankAccountDtoRequest.convert(userService);
        this.bankAccountService.saveBankAccount(bankAccount);
        return ResponseEntity.created(null).body(new BankAccountDtoResponse(bankAccount));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountDtoResponse> updateAccount(@PathVariable Long id, @RequestBody @Valid BankAccountDtoRequest bankAccountDtoRequest) {
       BankAccount bankAccountUpdated = this.bankAccountService.update(id, bankAccountDtoRequest);
        return new ResponseEntity<>(new BankAccountDtoResponse(bankAccountUpdated), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        bankAccountService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
