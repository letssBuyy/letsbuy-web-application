package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.entities.Withdraw;
import com.application.letsbuy.internal.enums.ActiveInactiveEnum;
import com.application.letsbuy.internal.services.UserService;
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
public class WithdrawDtoResponse {

    @NotNull
    private Long userId;

    @NotNull
    private Double amount;

    private LocalDateTime createdAt;

    public static List<WithdrawDtoResponse> convert(List<Withdraw> withdraws) {
        return withdraws.stream().map(WithdrawDtoResponse::new).collect(Collectors.toList());
    }

    public WithdrawDtoResponse(Withdraw withdraw) {
        this.userId = withdraw.getId();
        this.amount = withdraw.getAmount();
        this.createdAt = withdraw.getCreatedAt();
    }
}
