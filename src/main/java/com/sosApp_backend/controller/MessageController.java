package com.sosApp_backend.controller;

import com.sosApp_backend.model.Message;
import com.sosApp_backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/message")
@CrossOrigin("*")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createMessage(@RequestBody Message message) {
        Map<String, Object> response = new HashMap<>();

        if (message.getContent() == null || message.getContent().isEmpty()) {
            response.put("status", false);
            response.put("message", "El contenido del mensaje no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (message.getUser() == null || message.getUser().getUser_id() == null) {
            response.put("status", false);
            response.put("message", "El usuario asociado al mensaje no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (message.getChat() == null || message.getChat().getChat_id() == null) {
            response.put("status", false);
            response.put("message", "El chat asociado al mensaje no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Message createdMessage = messageService.create(message);
        response.put("status", true);
        response.put("message", "Mensaje creado correctamente");
        response.put("data", createdMessage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAll();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Map<String, Object>> getMessageById(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID del mensaje no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Message message = messageService.getById(id);
        if (message == null) {
            response.put("status", false);
            response.put("message", "No se encontró el mensaje con el ID proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("message", "Mensaje encontrado");
        response.put("data", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateMessage(@PathVariable UUID id, @RequestBody Message message) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID del mensaje no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (message.getContent() == null || message.getContent().isEmpty()) {
            response.put("status", false);
            response.put("message", "El contenido del mensaje no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Message updatedMessage = messageService.update(id, message);
        if (updatedMessage == null) {
            response.put("status", false);
            response.put("message", "No se pudo actualizar el mensaje. ID no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("message", "Mensaje actualizado correctamente");
        response.put("data", updatedMessage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteMessage(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID del mensaje no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        boolean deleted = messageService.delete(id);
        if (!deleted) {
            response.put("status", false);
            response.put("message", "No se pudo eliminar el mensaje. ID no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("message", "Mensaje eliminado correctamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<Map<String, Object>> getMessagesByChatId(@PathVariable UUID chatId) {
        Map<String, Object> response = new HashMap<>();

        if (chatId == null) {
            response.put("status", false);
            response.put("message", "El ID del chat no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        List<Message> messages = messageService.getByChatId(chatId);
        if (messages == null || messages.isEmpty()) {
            response.put("status", false);
            response.put("message", "No se encontraron mensajes para el chat con el ID proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("messages", messages);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
