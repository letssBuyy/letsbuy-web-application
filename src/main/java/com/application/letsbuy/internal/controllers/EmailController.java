package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.api.usecase.Sendable;
import com.application.letsbuy.internal.dto.ReceiverDto;
import com.application.letsbuy.internal.services.EmailGmailService;
import com.application.letsbuy.internal.services.EmailOutlookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")
public class EmailController {

    @PostMapping("/welcome")
    public ResponseEntity<Void> sendWelcome(@RequestBody ReceiverDto receiver){
        try {
            Sendable provider = getProvider(receiver.getEmail());
            return provider.sendWelcome(receiver.getName(), receiver.getEmail())?ResponseEntity.status(200).build():ResponseEntity.status(500).build();

        } catch (Exception e){
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping("/trocar-senha")
    public ResponseEntity<Void> sendChangePassword(@RequestBody ReceiverDto receiver){
        try {
            Sendable provider = getProvider(receiver.getEmail());
            return provider.sendChangePassword(receiver.getName(), receiver.getEmail())?ResponseEntity.status(200).build():ResponseEntity.status(500).build();

        } catch (Exception e){
            return ResponseEntity.status(400).build();
        }
    }
    private Sendable getProvider(String email){
        if (email.endsWith("@gmail.com")){
            return new EmailGmailService();
        } else if (email.endsWith("@outlook.com") || email.endsWith("@sptech.school")){
            return new EmailOutlookService();
        }
        return null;
    }
}
