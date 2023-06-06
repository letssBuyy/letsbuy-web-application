package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.entities.Withdraw;
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
public class WithdrawDtoRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Double amount;

    private LocalDateTime createdAt;

    public Withdraw convert(UserService userService) {
        User user = userService.findById(userId);
        return new Withdraw(amount, user);
    }
}
