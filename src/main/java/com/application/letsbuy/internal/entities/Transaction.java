package com.application.letsbuy.internal.entities;

import com.application.letsbuy.internal.enums.TransactionTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "bank_transaction")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double amount;

    @Column
    private LocalDateTime createdAt;

    private TransactionTypeEnum transactionType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Transaction(Double amount,TransactionTypeEnum transactionType, User user) {
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
        this.transactionType = transactionType;
        this.user = user;
    }
}
