package com.application.letsbuy.internal.controllers;

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
            if (receiver.getEmail().endsWith("@gmail.com")){
                EmailGmailService emailGmailService = new EmailGmailService();
                emailGmailService.sendWelcome(receiver.getName(), receiver.getEmail());
                return ResponseEntity.status(200).build();
            } else if (receiver.getEmail().endsWith("@outlook.com") || receiver.getEmail().endsWith("@sptech.school")){
                EmailOutlookService emailOutlookService = new EmailOutlookService();
                emailOutlookService.sendWelcome(receiver.getName(),receiver.getEmail());
                return ResponseEntity.status(200).build();
            }
            return ResponseEntity.status(400).build();


        } catch (Exception e){
            return ResponseEntity.status(404).build();
        }
    }
}
