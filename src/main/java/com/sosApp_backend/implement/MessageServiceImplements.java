package com.sosApp_backend.implement;

import com.sosApp_backend.model.Message;
import com.sosApp_backend.repository.MessageRepository;
import com.sosApp_backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImplements implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message create(Message message) {
        // Guarda un nuevo mensaje en la base de datos
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAll() {
        // Retorna todos los mensajes
        return messageRepository.findAll();
    }

    @Override
    public Message getById(UUID id) {
        // Busca un mensaje por su ID
        return messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with ID: " + id));
    }

    @Override
    public Message update(UUID id, Message message) {
        // Actualiza un mensaje si existe
        Message existingMessage = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with ID: " + id));
        existingMessage.setContent(message.getContent());
        existingMessage.setChat(message.getChat());
        existingMessage.setUser(message.getUser());
        existingMessage.setCreated_at(message.getCreated_at());
        return messageRepository.save(existingMessage);
    }

    @Override
    public boolean delete(UUID id) {
        // Elimina un mensaje por su ID
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Message> getByChatId(UUID chatId) {
        // Retorna todos los mensajes asociados a un chat específico
        return messageRepository.findByChatId(chatId);
    }
}
