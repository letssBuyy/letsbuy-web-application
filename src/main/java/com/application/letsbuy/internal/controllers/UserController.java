package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.*;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.entities.Transaction;
import com.application.letsbuy.internal.enums.TransactionTypeEnum;
import com.application.letsbuy.internal.exceptions.InsufficientBalanceException;
import com.application.letsbuy.internal.services.*;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final BankAccountService bankAccountService;
    private final AdversimentService adversimentService;
    private final TransactionService transactionService;

    @ApiOperation("Method used to register users")
    @PostMapping
    public ResponseEntity<UserDtoResponse> createUser(@RequestBody @Valid UserDto dto, UriComponentsBuilder uriBuilder) {
        User user = dto.convert();
        this.userService.save(user);
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDtoResponse(user));
    }

    @ApiOperation("Method used select user adversiments ")
    @GetMapping
    public ResponseEntity<UserAdversimentsDtoResponse> listarUser(@RequestParam Optional<Long> buyerId,
                                                                  @RequestParam Long sellerId) {
        return ResponseEntity.ok().body(this.userService.listarUser(buyerId, sellerId));
    }

    @ApiOperation("Method used to change user data")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDtoResponse> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDto userDto) {
        User user = userDto.update(id, userService, bankAccountService);
        return ResponseEntity.ok(new UserDtoResponse(user));
    }

    @ApiOperation("Method used to change to user password")
    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody @Valid PasswordUpdateDto dto) {
        dto.updatePassword(id, userService);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Method used to delete a user")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @ApiOperation("Whatsapp link generator")
    @GetMapping("/generateWppLink/{id}")
    public String generateWppLink(@PathVariable Long id){
        return this.userService.generateWppLink(id);
    }


    @ApiOperation("Method used to transaction money")
    @PatchMapping("/transaction")
    public ResponseEntity<BalanceDtoResponse> transactionMoney(@RequestBody @Valid TransactionRequestDto dto) {
        return ResponseEntity.ok(userService.transactionMoney(dto));
    }

    @ApiOperation("method used to count quantity users")
    @GetMapping("/qtd-users")
    public ResponseEntity<Long> quantityUser(){
        return ResponseEntity.ok(userService.quantityUsers());

    }

    @ApiOperation("Method used to list transactions")
    @GetMapping("/transaction/{idUser}")
    public ResponseEntity<BalanceDtoResponse> listTransactions(@PathVariable Long idUser) {
        User user = userService.findById(idUser);
        return ResponseEntity.ok(new BalanceDtoResponse(user.getBalance(), transactionService.listTransactions(idUser)));
    }
}



