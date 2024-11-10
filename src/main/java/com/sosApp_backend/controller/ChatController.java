package com.sosApp_backend.controller;

import com.sosApp_backend.model.Chat;
import com.sosApp_backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chat")
@CrossOrigin("*")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/create")
    public Chat createChat(@RequestBody Chat chat) {
        // Crear un nuevo chat
        return chatService.create(chat);
    }

    @GetMapping("/all")
    public List<Chat> getAllChats() {
        // Obtener todos los chats
        return chatService.getAll();
    }

    @GetMapping("/id/{id}")
    public Chat getChatById(@PathVariable UUID id) {
        // Obtener un chat por su ID
        return chatService.getById(id);
    }

    @PutMapping("/update/{id}")
    public Chat updateChat(@PathVariable UUID id, @RequestBody Chat chat) {
        // Actualizar un chat por su ID
        return chatService.update(id, chat);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteChat(@PathVariable UUID id) {
        // Eliminar un chat por su ID
        chatService.delete(id);
    }

    @GetMapping("/community/{communityId}")
    public Chat getChatByCommunityId(@PathVariable UUID communityId) {
        // Obtener un chat asociado a una comunidad específica
        return chatService.getByCommunityId(communityId);
    }
}
