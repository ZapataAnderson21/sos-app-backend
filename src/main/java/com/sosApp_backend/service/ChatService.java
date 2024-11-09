package com.sosApp_backend.service;

import com.sosApp_backend.model.Chat;
import java.util.List;
import java.util.UUID;

public interface ChatService {
    Chat create(Chat chat);
    List<Chat> getAll();
    Chat getById(UUID id);
    Chat update(UUID id, Chat chat);
    void delete(UUID id);
    Chat getByCommunityId(UUID communityId);
}
