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
public class MessageProposalResponseDto {

    private Long id;
    private Long idUser;
    private Double amount;
    private LocalDateTime postedAt;

    public MessageProposalResponseDto(Message message) {
        this.id = message.getId();
        this.amount = message.getAmount();
        this.postedAt = message.getPostedAt();
        this.idUser = message.getIdUser();
    }

    public static List<MessageProposalResponseDto> convert(List<Message> messages) {
        return messages.stream().map(MessageProposalResponseDto::new).collect(Collectors.toList());
    }
}
