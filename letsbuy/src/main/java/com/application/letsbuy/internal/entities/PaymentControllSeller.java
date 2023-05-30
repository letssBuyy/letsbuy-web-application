package com.application.letsbuy.internal.entities;

import com.application.letsbuy.internal.enums.PaymentControllSellerEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PaymentControllSeller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User admin;
    private PaymentControllSellerEnum status;
    private LocalDateTime expirationDate;
    private BigDecimal amountTax;
}
