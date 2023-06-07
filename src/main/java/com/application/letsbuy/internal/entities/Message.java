package com.application.letsbuy.internal.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private Long idUser;

    private LocalDateTime postedAt;

    private Boolean isProposal;

    private Double amount;
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public Message(Long idUser, LocalDateTime postedAt, Double amount, Chat chat) {
        this.message = "";
        this.idUser = idUser;
        this.postedAt = postedAt;
        this.amount = amount;
        this.chat = chat;
        this.isProposal = true;
    }

    public Message(String message, Long idUser, LocalDateTime postedAt, Chat chat) {
        this.message = message;
        this.postedAt = postedAt;
        this.idUser = idUser;
        this.chat = chat;
        this.isProposal = false;
        this.amount = 0.0;
    }
}
