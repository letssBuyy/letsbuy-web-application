package com.application.letsbuy.internal.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ManyToOne
    private User buyer;
    @ManyToOne
    private User seller;
    private Double value;
    private String createdAt;

    public Order(User buyer, User seller, Double value, String createdAt) {
        this.buyer = buyer;
        this.seller = seller;
        this.value = value;
        this.createdAt = createdAt;
    }
}
