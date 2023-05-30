package com.application.letsbuy.internal.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private String cupomUser;
    private String currencyId;
    private LocalDateTime createdAt;
    private String externalReference;
    private String initPoint;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String status;
}
