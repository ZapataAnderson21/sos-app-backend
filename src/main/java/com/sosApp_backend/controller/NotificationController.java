package com.sosApp_backend.controller;

import com.sosApp_backend.model.Notification;
import com.sosApp_backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
@CrossOrigin("*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/create")
    public Notification createNotification(@RequestBody Notification notification) {
        // Crear una nueva notificación
        return notificationService.create(notification);
    }

    @GetMapping("/all")
    public List<Notification> getAllNotifications() {
        // Obtener todas las notificaciones
        return notificationService.getAll();
    }

    @GetMapping("/id/{id}")
    public Notification getNotificationById(@PathVariable UUID id) {
        // Obtener una notificación por su ID
        return notificationService.getById(id);
    }

    @PutMapping("/update/{id}")
    public Notification updateNotification(@PathVariable UUID id, @RequestBody Notification notification) {
        // Actualizar una notificación por su ID
        return notificationService.update(id, notification);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNotification(@PathVariable UUID id) {
        // Eliminar una notificación por su ID
        notificationService.delete(id);
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getNotificationsByUserId(@PathVariable UUID userId) {
        // Obtener todas las notificaciones asociadas a un usuario específico
        return notificationService.getByUserId(userId);
    }
}
