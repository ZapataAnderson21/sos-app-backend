package com.sosApp_backend.implement;

import com.sosApp_backend.model.Notification;
import com.sosApp_backend.repository.NotificationRepository;
import com.sosApp_backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationServiceImplements implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification create(Notification notification) {
        // Guarda una nueva notificación en la base de datos
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAll() {
        // Retorna todas las notificaciones
        return notificationRepository.findAll();
    }

    @Override
    public Notification getById(UUID id) {
        // Busca una notificación por su ID
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with ID: " + id));
    }

    @Override
    public Notification update(UUID id, Notification notification) {
        // Actualiza una notificación si existe
        Notification existingNotification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with ID: " + id));
        existingNotification.setContent(notification.getContent());
        existingNotification.setCreated_at(notification.getCreated_at());
        existingNotification.setUser(notification.getUser());
        return notificationRepository.save(existingNotification);
    }

    @Override
    public boolean delete(UUID id) {
        // Elimina una notificación por su ID
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Notification> getByUserId(UUID userId) {
        // Retorna todas las notificaciones asociadas a un usuario específico
        return notificationRepository.findByUserId(userId);
    }
}
