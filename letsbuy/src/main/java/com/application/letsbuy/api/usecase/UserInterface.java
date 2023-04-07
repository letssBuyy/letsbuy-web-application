package com.application.letsbuy.api.usecase;

import com.application.letsbuy.internal.entities.User;

public interface UserInterface {

     void save(User user);
     User findByName(String name);
     User findById(Long id);
      void deleteById(Long id);
}
