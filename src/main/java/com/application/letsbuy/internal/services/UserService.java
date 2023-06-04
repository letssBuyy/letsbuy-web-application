package com.application.letsbuy.internal.services;

import com.application.letsbuy.api.usecase.UserInterface;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.enums.ActiveInactiveEnum;
import com.application.letsbuy.internal.exceptions.UserConflictException;
import com.application.letsbuy.internal.exceptions.UserNotFoundException;
import com.application.letsbuy.internal.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Service
public class UserService implements UserInterface {
    private final UserRepository userRepository;
    private final ImageService imageService;

    @Override
    public void save(User user) {
        if (isUserAlreadyRegistered(user.getEmail(), user.getCpf())) {
            throw new UserConflictException();
        }
        this.userRepository.save(user);
    }

    @Override
    public User findByName(String name) {
        return this.userRepository.findByName(name).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setIsActive(ActiveInactiveEnum.INACTIVE);
    }

    public User insertProfileImage(Long id, MultipartFile img) {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setProfileImage(imageService.upload(img));
        this.userRepository.save(user);
        return user;
    }

    public User deleteProfileImage(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setProfileImage(null);
        this.userRepository.save(user);
        return user;
    }

    private boolean isUserAlreadyRegistered(String email, String cpf) {
        return userRepository.findByEmail(email).isPresent() || userRepository.findByCpf(cpf).isPresent();
    }

    @Override
    public String generateWppLink(Long id) {
        return "wa.me/55" + findById(id).getPhoneNumber();
    }
}
