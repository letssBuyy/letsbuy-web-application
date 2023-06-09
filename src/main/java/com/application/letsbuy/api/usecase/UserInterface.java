package com.application.letsbuy.api.usecase;

import com.application.letsbuy.internal.dto.BalanceDtoResponse;
import com.application.letsbuy.internal.dto.TransactionRequestDto;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.entities.Transaction;

public interface UserInterface {

    void save(User user);

    User findByName(String name);

    User findById(Long id);

    void deleteById(Long id);

    User findByEmail(String email);

    String generateWppLink(Long id);

    BalanceDtoResponse transactionMoney(TransactionRequestDto dto);
}
