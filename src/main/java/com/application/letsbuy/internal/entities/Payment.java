package com.application.letsbuy.internal.entities;

import com.application.letsbuy.internal.enums.PaymentStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private String cupomUser;

    private String description;

    private String currencyId;

    private LocalDateTime createdAt;

    private String externalReference;

    private String initPoint;

    private LocalDateTime paymentDate;

    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum status;
}
