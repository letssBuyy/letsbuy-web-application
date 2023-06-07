package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.dto.ChatRequestDto;
import com.application.letsbuy.internal.dto.ChatResponseDto;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.Chat;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.exceptions.AdversimentNotFoundException;
import com.application.letsbuy.internal.exceptions.UserNotFoundException;
import com.application.letsbuy.internal.repositories.AdversimentRepository;
import com.application.letsbuy.internal.repositories.ChatRepository;
import com.application.letsbuy.internal.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ChatService {

    private ChatRepository chatRepository;
    private UserRepository userRepository;
    private AdversimentRepository adversimentRepository;

    public List<Chat> listChats(Long idUser) {

        return chatRepository.findChatBySellerIdOrBuyerId(idUser, idUser);
    }

    public ChatResponseDto register(ChatRequestDto chatDto){

        Boolean alreadyExists = chatRepository.existsChatBySellerIdAndBuyerIdAndAdversimentId(
                chatDto.getIdSeller(),
                chatDto.getIdBuyer(),
                chatDto.getIdAdversiment()
        );

        if (alreadyExists){
            return new ChatResponseDto(
                    chatRepository.findChatBySellerIdAndBuyerIdAndAdversimentId(
                            chatDto.getIdSeller(),
                            chatDto.getIdBuyer(),
                            chatDto.getIdAdversiment()
                    ).get()
            );
        }

        Optional<User> sellerOptional = userRepository.findById(chatDto.getIdSeller());
        Optional<User> buyerOptional = userRepository.findById(chatDto.getIdBuyer());

        if (sellerOptional.isEmpty() || buyerOptional.isEmpty()){
            throw new UserNotFoundException();
        }

        Optional<Adversiment> adversimentOptional = adversimentRepository.findById(chatDto.getIdAdversiment());

        if (adversimentOptional.isEmpty()){
            throw new AdversimentNotFoundException();
        }

        return new ChatResponseDto(chatRepository.save(new Chat(buyerOptional.get(),sellerOptional.get(),adversimentOptional.get())));
    }
}
