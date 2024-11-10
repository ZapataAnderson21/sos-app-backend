package com.sosApp_backend.controller;

import com.sosApp_backend.model.Notification;
import com.sosApp_backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/notification")
@CrossOrigin("*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createNotification(@RequestBody Notification notification) {
        Map<String, Object> response = new HashMap<>();

        if (notification.getContent() == null || notification.getContent().isEmpty()) {
            response.put("status", false);
            response.put("message", "El contenido de la notificación no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (notification.getUser() == null || notification.getUser().getUser_id() == null) {
            response.put("status", false);
            response.put("message", "El usuario asociado a la notificación no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Notification createdNotification = notificationService.create(notification);
        response.put("status", true);
        response.put("message", "Notificación creada correctamente");
        response.put("data", createdNotification);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAll();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Map<String, Object>> getNotificationById(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID de la notificación no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Notification notification = notificationService.getById(id);
        if (notification == null) {
            response.put("status", false);
            response.put("message", "No se encontró la notificación con el ID proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("message", "Notificación encontrada");
        response.put("data", notification);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateNotification(@PathVariable UUID id, @RequestBody Notification notification) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID de la notificación no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (notification.getContent() == null || notification.getContent().isEmpty()) {
            response.put("status", false);
            response.put("message", "El contenido de la notificación no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Notification updatedNotification = notificationService.update(id, notification);
        if (updatedNotification == null) {
            response.put("status", false);
            response.put("message", "No se pudo actualizar la notificación. ID no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("message", "Notificación actualizada correctamente");
        response.put("data", updatedNotification);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteNotification(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID de la notificación no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        boolean deleted = notificationService.delete(id);
        if (!deleted) {
            response.put("status", false);
            response.put("message", "No se pudo eliminar la notificación. ID no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("message", "Notificación eliminada correctamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getNotificationsByUserId(@PathVariable UUID userId) {
        Map<String, Object> response = new HashMap<>();

        if (userId == null) {
            response.put("status", false);
            response.put("message", "El ID del usuario no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        List<Notification> notifications = notificationService.getByUserId(userId);
        if (notifications == null || notifications.isEmpty()) {
            response.put("status", false);
            response.put("message", "No se encontraron notificaciones para el usuario con el ID proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("notifications", notifications);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
