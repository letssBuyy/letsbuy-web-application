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
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public Message(String message, Long idUser, LocalDateTime postedAt, Chat chat) {
        this.message = message;
        this.postedAt = postedAt;
        this.idUser = idUser;
        this.chat = chat;
    }
}
