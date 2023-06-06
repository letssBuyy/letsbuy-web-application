package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.ChatRequestDto;
import com.application.letsbuy.internal.dto.ChatResponseDto;
import com.application.letsbuy.internal.dto.MessageRequestDto;
import com.application.letsbuy.internal.dto.MessageResponseDto;
import com.application.letsbuy.internal.entities.Chat;
import com.application.letsbuy.internal.entities.Message;
import com.application.letsbuy.internal.services.ChatService;
import com.application.letsbuy.internal.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;

    @GetMapping("/{idChat}")
    public ResponseEntity<List<MessageResponseDto>> list(@PathVariable Long idChat) {

        List<MessageResponseDto> messages = messageService.listByChat(idChat);

        if (messages.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.ok(messages);
    }

    @PostMapping
    public ResponseEntity<MessageResponseDto> register(@RequestBody MessageRequestDto messageDto) {
        return ResponseEntity.status(201).body(messageService.register(messageDto));
    }
}
