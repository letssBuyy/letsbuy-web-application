package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Chat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ChatResponseDto {

    private Long id;
    private UserDtoResponse seller;
    private UserDtoResponse buyer;
    private AdversimentDtoResponse adversiment;

    public ChatResponseDto(Chat chat){
        id = chat.getId();
        seller = new UserDtoResponse(chat.getSeller());
        buyer = new UserDtoResponse(chat.getBuyer());
        adversiment = new AdversimentDtoResponse(chat.getAdversiment());
    }

    public static List<ChatResponseDto> convert(List<Chat> chats) {
        return chats.stream().map(ChatResponseDto::new).collect(Collectors.toList());
    }
}
