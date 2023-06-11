package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.dto.AdversimentDtoResponse;
import com.application.letsbuy.internal.dto.ChatRequestDto;
import com.application.letsbuy.internal.dto.ChatResponseDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ChatService {

    private ChatRepository chatRepository;
    private UserRepository userRepository;
    private AdversimentRepository adversimentRepository;
    private MessageRepository messageRepository;

    public List<ChatResponseDto> listChats(Long idUser) {

        List<Chat> listChats = chatRepository.findChatBySellerIdOrBuyerId(idUser, idUser);

        List<ChatResponseDto> listDto = new ArrayList<>();

        if(listChats.size() > 0){

            listChats.forEach((chat)->{

                List<Message> listMessages = messageRepository.findByChatId(chat.getId());

                listDto.add(new ChatResponseDto(chat,listMessages.get(listMessages.size()-1).getPostedAt()));
            });

        }

        return listDto;

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

    public AdversimentDtoResponse acceptProposal(Long idProposal) {

        Optional<Message> optionalProposal = messageRepository.findById(idProposal);

        if (optionalProposal.isEmpty() || !optionalProposal.get().getIsProposal()) {
            throw new ChatNotFoundException();
        }

        optionalProposal.get().getChat().getAdversiment().setPrice(optionalProposal.get().getAmount());

        return new AdversimentDtoResponse(adversimentRepository.save(optionalProposal.get().getChat().getAdversiment()));
    }
}