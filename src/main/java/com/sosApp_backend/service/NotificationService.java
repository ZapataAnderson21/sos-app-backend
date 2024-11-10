package com.sosApp_backend.service;

import com.sosApp_backend.model.Notification;
import java.util.List;
import java.util.UUID;

public interface NotificationService {
    Notification create(Notification notification);
    List<Notification> getAll();
    Notification getById(UUID id);
    Notification update(UUID id, Notification notification);
    boolean delete(UUID id);
    List<Notification> getByUserId(UUID userId);
}
