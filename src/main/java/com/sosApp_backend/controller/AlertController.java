package com.sosApp_backend.controller;

import com.sosApp_backend.model.Alert;
import com.sosApp_backend.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alert")
@CrossOrigin("*")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping("/create")
    public Alert createAlert(@RequestBody Alert alert) {
        // Crear una nueva alerta
        return alertService.create(alert);
    }

    @GetMapping("/all")
    public List<Alert> getAllAlerts() {
        // Obtener todas las alertas
        return alertService.getAll();
    }

    @GetMapping("/id/{id}")
    public Alert getAlertById(@PathVariable UUID id) {
        // Obtener una alerta por su ID
        return alertService.getById(id);
    }

    @PutMapping("/update/{id}")
    public Alert updateAlert(@PathVariable UUID id, @RequestBody Alert alert) {
        // Actualizar una alerta por su ID
        return alertService.update(id, alert);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAlert(@PathVariable UUID id) {
        // Eliminar una alerta por su ID
        alertService.delete(id);
    }

    @GetMapping("/user/{userId}")
    public List<Alert> getAlertsByUserId(@PathVariable UUID userId) {
        // Obtener todas las alertas asociadas a un usuario
        return alertService.getByUserId(userId);
    }

    @GetMapping("/community/{communityId}")
    public List<Alert> getAlertsByCommunityId(@PathVariable UUID communityId) {
        // Obtener todas las alertas asociadas a una comunidad
        return alertService.getByCommunityId(communityId);
    }
}
