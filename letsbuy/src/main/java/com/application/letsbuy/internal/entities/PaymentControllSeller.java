package com.application.letsbuy.internal.entities;

import com.application.letsbuy.internal.enums.PaymentControllSellerEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "payment_controll_seller")
public class PaymentControllSeller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User admin;

    @Enumerated(EnumType.STRING)
    @Column
    private PaymentControllSellerEnum status;

    @Column
    private LocalDateTime expirationDate;

    @Column
    private Double amountTax;
}