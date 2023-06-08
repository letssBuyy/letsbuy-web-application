package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.entities.Transaction;
import com.application.letsbuy.internal.enums.TransactionTypeEnum;
import com.application.letsbuy.internal.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
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
