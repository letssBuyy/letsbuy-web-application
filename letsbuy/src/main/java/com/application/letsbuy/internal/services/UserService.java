package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.exceptions.UserNotFoundException;
import com.application.letsbuy.internal.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public User findById(Long id) {
        Optional<User> retrieveUserById = userRepository.findById(id);
        if (retrieveUserById.isPresent()) {
            return retrieveUserById.get();
        }
        throw new UserNotFoundException();
    }

    public void deleteById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
    }

    public User findByName(String name) {
        Optional<User> retrieveUserByName = userRepository.findByName(name);
        if (retrieveUserByName.isPresent()) {
            return retrieveUserByName.get();
        }
        throw new UserNotFoundException();
    }
}
