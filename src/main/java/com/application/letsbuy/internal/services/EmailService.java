package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.dto.ReceiverDto;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.exceptions.UserNotFoundException;
import com.application.letsbuy.internal.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final UserRepository userRepository;

    public ReceiverDto getReceiverName(ReceiverDto receiver) {
        User user = this.userRepository.findByEmail(receiver.getEmail()).orElseThrow(UserNotFoundException::new);
        receiver.setNome(user.getName());
        return receiver;
    }

    public String gerReceiverId(ReceiverDto receiverDto) {
        User user = this.userRepository.findByEmail(receiverDto.getEmail()).orElseThrow(UserNotFoundException::new);
        return user.getId().toString();
    }
}
