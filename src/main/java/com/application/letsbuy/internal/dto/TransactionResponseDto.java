package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Transaction;
import com.application.letsbuy.internal.enums.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {

    @NotNull
    private Long userId;

    @NotNull
    private Double amount;

    private TransactionTypeEnum transactionType;

    private LocalDateTime createdAt;

    public static List<TransactionResponseDto> convert(List<Transaction> transactions) {
        return transactions.stream().map(TransactionResponseDto::new).collect(Collectors.toList());
    }

    public TransactionResponseDto(Transaction transaction) {
        this.userId = transaction.getId();
        this.amount = transaction.getAmount();
        this.transactionType = transaction.getTransactionType();
        this.createdAt = transaction.getCreatedAt();
    }
}
