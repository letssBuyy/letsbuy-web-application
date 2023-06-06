package com.application.letsbuy.internal.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "adversiments_like")
public class AdversimentsLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Adversiment adversiment;

    public AdversimentsLike(User user, Adversiment adversiment) {
        this.user = user;
        this.adversiment = adversiment;
    }
}
