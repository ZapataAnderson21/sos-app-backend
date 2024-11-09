package com.sosApp_backend.impl;

import com.sosApp_backend.model.SecurityPerson;
import com.sosApp_backend.repository.SecurityPersonRepository;
import com.sosApp_backend.service.SecurityPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SecurityPersonServiceImplements implements SecurityPersonService {

    @Autowired
    private SecurityPersonRepository securityPersonRepository;

    @Override
    public SecurityPerson create(SecurityPerson securityPerson) {
        // Guarda una nueva persona de seguridad en la base de datos
        return securityPersonRepository.save(securityPerson);
    }

    @Override
    public List<SecurityPerson> getAll() {
        // Retorna todas las personas de seguridad
        return securityPersonRepository.findAll();
    }

    @Override
    public SecurityPerson getById(UUID id) {
        // Busca una persona de seguridad por su ID
        return securityPersonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Security person not found with ID: " + id));
    }

    @Override
    public SecurityPerson update(UUID id, SecurityPerson securityPerson) {
        // Actualiza una persona de seguridad si existe
        SecurityPerson existingPerson = securityPersonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Security person not found with ID: " + id));
        existingPerson.setFull_name(securityPerson.getFull_name());
        existingPerson.setDni(securityPerson.getDni());
        existingPerson.setPassword(securityPerson.getPassword());
        existingPerson.setSecurityEntity(securityPerson.getSecurityEntity());
        return securityPersonRepository.save(existingPerson);
    }

    @Override
    public void delete(UUID id) {
        // Elimina una persona de seguridad por su ID
        if (!securityPersonRepository.existsById(id)) {
            throw new RuntimeException("Security person not found with ID: " + id);
        }
        securityPersonRepository.deleteById(id);
    }

    @Override
    public List<SecurityPerson> getBySecurityEntityId(UUID securityEntityId) {
        // Retorna todas las personas de seguridad asociadas a una entidad de seguridad específica
        return securityPersonRepository.findBySecurityEntityId(securityEntityId);
    }
}
