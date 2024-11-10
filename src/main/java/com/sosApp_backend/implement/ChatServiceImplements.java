package com.sosApp_backend.implement;

import com.sosApp_backend.model.Chat;
import com.sosApp_backend.repository.ChatRepository;
import com.sosApp_backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatServiceImplements implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat create(Chat chat) {
        // Guarda un nuevo chat en la base de datos
        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> getAll() {
        // Retorna todos los chats
        return chatRepository.findAll();
    }

    @Override
    public Chat getById(UUID id) {
        // Busca un chat por su ID
        return chatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat not found with ID: " + id));
    }

    @Override
    public Chat update(UUID id, Chat chat) {
        // Actualiza un chat si existe
        Chat existingChat = chatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat not found with ID: " + id));
        existingChat.setCommunity(chat.getCommunity());
        return chatRepository.save(existingChat);
    }

    @Override
    public void delete(UUID id) {
        // Elimina un chat por su ID
        if (!chatRepository.existsById(id)) {
            throw new RuntimeException("Chat not found with ID: " + id);
        }
        chatRepository.deleteById(id);
    }

    @Override
    public Chat getByCommunityId(UUID communityId) {
        // Busca un chat asociado a una comunidad específica
        return chatRepository.findByCommunityId(communityId);
    }
}
