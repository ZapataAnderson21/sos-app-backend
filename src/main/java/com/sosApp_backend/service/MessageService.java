package com.sosApp_backend.service;

import com.sosApp_backend.model.Message;
import java.util.List;
import java.util.UUID;

public interface MessageService {
    Message create(Message message);
    List<Message> getAll();
    Message getById(UUID id);
    Message update(UUID id, Message message);
    void delete(UUID id);
    List<Message> getByChatId(UUID chatId);
}
