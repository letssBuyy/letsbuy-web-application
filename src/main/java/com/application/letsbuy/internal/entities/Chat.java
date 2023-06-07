package com.application.letsbuy.internal.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User buyer;

    @ManyToOne
    private User seller;

    @JoinColumn(name = "adversiment_id")
    @ManyToOne
    private Adversiment adversiment;

    @JoinColumn(name = "message_id")
    @OneToMany
    private List<Message> messages;

    public Chat(User buyer, User seller, Adversiment adversiment) {
        this.buyer = buyer;
        this.seller = seller;
        this.adversiment = adversiment;
    }
}
