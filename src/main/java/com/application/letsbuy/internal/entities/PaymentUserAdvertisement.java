package com.application.letsbuy.internal.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "payment_user_advertisement")
public class PaymentUserAdvertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
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
