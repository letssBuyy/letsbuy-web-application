package com.application.letsbuy.internal.web.controllers;

import com.application.letsbuy.internal.dto.ReceiverDto;
import com.application.letsbuy.internal.services.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @PostMapping("/welcome")
    public Boolean sendWelcome(@RequestBody ReceiverDto receiver){
        EmailService emailService = new EmailService();
        try {
            emailService.sendWelcome(receiver.getNome(),receiver.getEmail());
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
