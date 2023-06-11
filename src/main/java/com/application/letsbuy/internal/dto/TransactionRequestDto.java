package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.enums.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDto {
    private Long adversimentId;

    private Long userId;

    private Double amount;

    private TransactionTypeEnum transactionType;

    private LocalDateTime createdAt;
}
