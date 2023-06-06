package com.application.letsbuy.api.usecase;

import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.entities.Withdraw;

public interface UserInterface {

    void save(User user);

    User findByName(String name);

    User findById(Long id);

    void deleteById(Long id);

    String generateWppLink(Long id);

    Double withdrawMoney(Withdraw withdraw);
}
