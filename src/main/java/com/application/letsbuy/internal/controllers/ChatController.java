package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.ChatRequestDto;
import com.application.letsbuy.internal.dto.ChatResponseDto;
import com.application.letsbuy.internal.entities.Chat;
import com.application.letsbuy.internal.services.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chats")
public class ChatController {

    private ChatService chatService;

    @GetMapping("/{idUser}")
    public ResponseEntity<List<ChatResponseDto>> list(@PathVariable Long idUser) {

        List<Chat> chats = chatService.listChats(idUser);

        if (chats.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.ok(ChatResponseDto.convert(chats));
    }

    @PostMapping
    public ResponseEntity<ChatResponseDto> register(@RequestBody ChatRequestDto chatDto) {
        return ResponseEntity.status(201).body(chatService.register(chatDto));
    }
}
