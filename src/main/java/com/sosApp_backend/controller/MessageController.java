package com.sosApp_backend.controller;

import com.sosApp_backend.model.Message;
import com.sosApp_backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
@CrossOrigin("*")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/create")
    public Message createMessage(@RequestBody Message message) {
        // Crear un nuevo mensaje
        return messageService.create(message);
    }

    @GetMapping("/all")
    public List<Message> getAllMessages() {
        // Obtener todos los mensajes
        return messageService.getAll();
    }

    @GetMapping("/id/{id}")
    public Message getMessageById(@PathVariable UUID id) {
        // Obtener un mensaje por su ID
        return messageService.getById(id);
    }

    @PutMapping("/update/{id}")
    public Message updateMessage(@PathVariable UUID id, @RequestBody Message message) {
        // Actualizar un mensaje por su ID
        return messageService.update(id, message);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMessage(@PathVariable UUID id) {
        // Eliminar un mensaje por su ID
        messageService.delete(id);
    }

    @GetMapping("/chat/{chatId}")
    public List<Message> getMessagesByChatId(@PathVariable UUID chatId) {
        // Obtener todos los mensajes asociados a un chat específico
        return messageService.getByChatId(chatId);
    }
}
