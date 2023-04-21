package com.application.letsbuy.internal.services;

import com.application.letsbuy.api.usecase.UserInterface;
import com.application.letsbuy.internal.entities.User;
//import com.application.letsbuy.internal.exceptions.ErrorNotFoundException;
import com.application.letsbuy.internal.exceptions.UserNotFoundException;
import com.application.letsbuy.internal.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements UserInterface {
    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByName(String name) {
        Optional<User> retrieveUserByName = userRepository.findByName(name);
        if (retrieveUserByName.isPresent()) {
            return retrieveUserByName.get();
        }
        throw new UserNotFoundException();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()
                -> new UserNotFoundException());
    }
    @Override
    public void deleteById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
    }
}
