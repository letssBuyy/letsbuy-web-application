package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.dto.WithdrawDtoResponse;
import com.application.letsbuy.internal.entities.Withdraw;
import com.application.letsbuy.internal.repositories.WithdrawRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WithdrawService {

    private final WithdrawRepository withdrawRepository;

    public void save(Withdraw withdraw){
        this.withdrawRepository.save(withdraw);
    }

    public List<WithdrawDtoResponse> listWithdraws(Long userId) {

        List<Withdraw> withdraws = withdrawRepository.findByUserId(userId);

        return WithdrawDtoResponse.convert(withdraws);
    }
}
