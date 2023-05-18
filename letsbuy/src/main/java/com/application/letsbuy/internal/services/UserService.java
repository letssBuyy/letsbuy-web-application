package com.application.letsbuy.internal.services;

import com.application.letsbuy.api.usecase.UserInterface;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.Image;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.enums.ActiveInactiveEnum;
import com.application.letsbuy.internal.exceptions.AdversimentNotFoundException;
import com.application.letsbuy.internal.exceptions.UserConflictException;
import com.application.letsbuy.internal.exceptions.UserNotFoundException;
import com.application.letsbuy.internal.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements UserInterface {
    private final UserRepository userRepository;
    private final ImageService imageService;

    @Override
    public void save(User user) {
        Optional<User> isUserExist = userRepository.findByEmail(user.getEmail());
        if (!(isUserExist.isPresent())) {
            userRepository.save(user);
        } else {
            throw new UserConflictException();
        }
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
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        }
        throw new UserNotFoundException();
    }
    @Override
    public void deleteById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            user.setIsActive(ActiveInactiveEnum.INACTIVE);
        } else {
            throw new UserNotFoundException();
        }
    }

    public User insertProfileImage(Long id, MultipartFile img){
        Optional<User> userFound = userRepository.findById(id);

        if (userFound.isEmpty()) {
            throw new UserNotFoundException();
        }

        User user = userFound.get();
        user.setProfileImage(imageService.upload(img));
        userRepository.save(user);
        return user;
    }
}
