package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.ReceiverDto;
import com.application.letsbuy.internal.services.EmailGmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @PostMapping("/welcome")
    public Boolean sendWelcome(@RequestBody ReceiverDto receiver){
        EmailGmailService emailGmailService = new EmailGmailService();
        try {
            emailGmailService.sendWelcome(receiver.getNome(), receiver.getEmail());
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
