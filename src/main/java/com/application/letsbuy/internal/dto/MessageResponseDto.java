package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class MessageResponseDto {

    private Long id;
    private String message;
    private Long idUser;
    private Boolean isProposal;
    private Double amount;
    private LocalDateTime postedAt;


    public MessageResponseDto(Message message) {
        this.id = message.getId();
        this.message = message.getMessage();
        this.idUser = message.getIdUser();
        this.postedAt = message.getPostedAt();
        this.amount = message.getAmount();
        this.isProposal = message.getIsProposal();
    }


    public static List<MessageResponseDto> convert(List<Message> messages) {
        return messages.stream().map(MessageResponseDto::new).collect(Collectors.toList());
    }
}
