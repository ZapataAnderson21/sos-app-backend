package com.sosApp_backend.implement;

import com.sosApp_backend.model.Alert;
import com.sosApp_backend.repository.AlertRepository;
import com.sosApp_backend.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlertServiceImplements implements AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Override
    public Alert create(Alert alert) {
        // Guarda una nueva alerta en la base de datos
        return alertRepository.save(alert);
    }

    @Override
    public List<Alert> getAll() {
        // Retorna todas las alertas
        return alertRepository.findAll();
    }

    @Override
    public Alert getById(UUID id) {
        // Busca una alerta por su ID
        return alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found with ID: " + id));
    }

    @Override
    public Alert update(UUID id, Alert alert) {
        // Actualiza una alerta si existe
        Alert existingAlert = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found with ID: " + id));
        existingAlert.setTitle(alert.getTitle());
        existingAlert.setDescription(alert.getDescription());
        existingAlert.setLocation(alert.getLocation());
        existingAlert.setUser(alert.getUser());
        existingAlert.setCommunity(alert.getCommunity());
        existingAlert.setCreated_at(alert.getCreated_at());
        return alertRepository.save(existingAlert);
    }

    @Override
    public void delete(UUID id) {
        // Elimina una alerta por su ID
        if (!alertRepository.existsById(id)) {
            throw new RuntimeException("Alert not found with ID: " + id);
        }
        alertRepository.deleteById(id);
    }

    @Override
    public List<Alert> getByUserId(UUID userId) {
        // Retorna todas las alertas asociadas a un usuario específico
        return alertRepository.findByUserId(userId);
    }

    @Override
    public List<Alert> getByCommunityId(UUID communityId) {
        // Retorna todas las alertas asociadas a una comunidad específica
        return alertRepository.findByCommunityId(communityId);
    }
}
