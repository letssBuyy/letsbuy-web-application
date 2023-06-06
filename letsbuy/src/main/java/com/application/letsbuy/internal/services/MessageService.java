package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.dto.ChatRequestDto;
import com.application.letsbuy.internal.dto.ChatResponseDto;
import com.application.letsbuy.internal.dto.MessageRequestDto;
import com.application.letsbuy.internal.dto.MessageResponseDto;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.Chat;
import com.application.letsbuy.internal.entities.Message;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.exceptions.AdversimentNotFoundException;
import com.application.letsbuy.internal.exceptions.ChatNotFoundException;
import com.application.letsbuy.internal.exceptions.UserNotFoundException;
import com.application.letsbuy.internal.repositories.AdversimentRepository;
import com.application.letsbuy.internal.repositories.ChatRepository;
import com.application.letsbuy.internal.repositories.MessageRepository;
import com.application.letsbuy.internal.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageService {

    private ChatRepository chatRepository;
    private MessageRepository messageRepository;

    public List<MessageResponseDto> listByChat(Long idChat) {

        Optional<Chat> chatOptional = chatRepository.findById(idChat);

        if (chatOptional.isEmpty()) {
            throw new ChatNotFoundException();
        }

        List<Message> messages = messageRepository.findByChatId(idChat);

        return MessageResponseDto.convert(messages);
    }

    public MessageResponseDto register(MessageRequestDto messageRequestDto) {

        Optional<Chat> chatOptional = chatRepository.findById(messageRequestDto.getIdChat());

        if (chatOptional.isEmpty()) {
            throw new ChatNotFoundException();
        }

        if (chatOptional.get().getBuyer().getId() != messageRequestDto.getIdUser() && chatOptional.get().getSeller().getId() != messageRequestDto.getIdUser()) {
            throw new UserNotFoundException();
        }

        return new MessageResponseDto(messageRepository.save(new Message(
                messageRequestDto.getMessage(),
                messageRequestDto.getIdUser(),
                LocalDateTime.now(),
                chatOptional.get()
        )));
    }
}
