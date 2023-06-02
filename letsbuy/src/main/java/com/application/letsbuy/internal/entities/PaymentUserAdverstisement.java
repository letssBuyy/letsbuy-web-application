package com.application.letsbuy.internal.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PaymentUserAdverstisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "adversiment_id")
    private Adversiment adversiment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Column
    private LocalDateTime expirationDate;

    @Column
    private LocalDateTime receivableDate;
}
