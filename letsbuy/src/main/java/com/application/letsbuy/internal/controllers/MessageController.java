package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.MessageProposalRequestDto;
import com.application.letsbuy.internal.dto.MessageProposalResponseDto;
import com.application.letsbuy.internal.dto.MessageRequestDto;
import com.application.letsbuy.internal.dto.MessageResponseDto;
import com.application.letsbuy.internal.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;

    @GetMapping("/{idChat}")
    public ResponseEntity<List<Map<String, Object>>> listOrder(@PathVariable Long idChat) {

        List<Map<String, Object>> messages = messageService.listByChat(idChat);

        if (messages.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.ok(messages);
    }

    @PostMapping
    public ResponseEntity<MessageResponseDto> register(@RequestBody MessageRequestDto messageDto) {
        return ResponseEntity.status(201).body(messageService.register(messageDto));
    }

    @PostMapping("/proposal")
    public ResponseEntity<MessageProposalResponseDto> registerProposal(@RequestBody MessageProposalRequestDto messageDto) {
        return ResponseEntity.status(201).body(messageService.registerProposal(messageDto));
    }
}
