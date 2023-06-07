package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.dto.MessageProposalRequestDto;
import com.application.letsbuy.internal.dto.MessageProposalResponseDto;
import com.application.letsbuy.internal.dto.MessageRequestDto;
import com.application.letsbuy.internal.dto.MessageResponseDto;
import com.application.letsbuy.internal.entities.Chat;
import com.application.letsbuy.internal.entities.Message;
import com.application.letsbuy.internal.exceptions.ChatNotFoundException;
import com.application.letsbuy.internal.exceptions.UserNotFoundException;
import com.application.letsbuy.internal.repositories.ChatRepository;
import com.application.letsbuy.internal.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@AllArgsConstructor
public class MessageService {

    private ChatRepository chatRepository;
    private MessageRepository messageRepository;

    public List<Map<String, Object>> listByChat(Long idChat) {

        Optional<Chat> chatOptional = chatRepository.findById(idChat);

        if (chatOptional.isEmpty()) {
            throw new ChatNotFoundException();
        }

        List<MessageResponseDto> messages =  MessageResponseDto.convert(messageRepository.findByChatId(idChat));

        Map<String, List<Map<String, Object>>> jsonFinal = new HashMap<>();


        for (MessageResponseDto m : messages) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
            String dataString = m.getPostedAt().format(dateFormatter);

            Map<String, Object> messageJson = new HashMap<>();
            messageJson.put("id", m.getId());
            messageJson.put("message", m.getMessage());
            messageJson.put("idUser", m.getIdUser());
            messageJson.put("amount", m.getAmount());
            messageJson.put("isProposal", m.getIsProposal());

            DateTimeFormatter timestampFormatter = DateTimeFormatter.ISO_DATE_TIME;
            String timestampString = m.getPostedAt().format(timestampFormatter);
            messageJson.put("postedAt", timestampString);

            if (jsonFinal.containsKey(dataString)) {
                List<Map<String, Object>> listaMensagens = jsonFinal.get(dataString);
                listaMensagens.add(messageJson);
            } else {
                List<Map<String, Object>> listaMensagens = new ArrayList<>();
                listaMensagens.add(messageJson);
                jsonFinal.put(dataString, listaMensagens);
            }
        }

        List<Map<String, Object>> list = new ArrayList<>();
        for (Map.Entry<String, List<Map<String, Object>>> entry : jsonFinal.entrySet()) {
            Map<String, Object> responseJson = new HashMap<>();
            responseJson.put("date", entry.getKey());
            responseJson.put("messages", entry.getValue());
            list.add(responseJson);
        }

        return list;
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

    public MessageProposalResponseDto registerProposal(MessageProposalRequestDto messageRequestDto) {

        Optional<Chat> chatOptional = chatRepository.findById(messageRequestDto.getIdChat());

        if (chatOptional.isEmpty()) {
            throw new ChatNotFoundException();
        }

        if (chatOptional.get().getBuyer().getId() != messageRequestDto.getIdUser() && chatOptional.get().getSeller().getId() != messageRequestDto.getIdUser()) {
            throw new UserNotFoundException();
        }

        return new MessageProposalResponseDto(messageRepository.save(new Message(
                messageRequestDto.getIdUser(),
                LocalDateTime.now(),
                messageRequestDto.getAmount(),
                chatOptional.get()
        )));
    }
}
