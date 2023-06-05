package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.entities.Withdraw;
import com.application.letsbuy.internal.repositories.WithdrawRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WithdrawService {

    private final WithdrawRepository withdrawRepository;

    public void save(Withdraw withdraw){
        this.withdrawRepository.save(withdraw);
    }
}
