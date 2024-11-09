package com.sosApp_backend.impl;

import com.sosApp_backend.model.SecurityEntity;
import com.sosApp_backend.repository.SecurityEntityRepository;
import com.sosApp_backend.service.SecurityEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SecurityEntityServiceImplements implements SecurityEntityService {

    @Autowired
    private SecurityEntityRepository securityEntityRepository;

    @Override
    public SecurityEntity create(SecurityEntity securityEntity) {
        // Guarda una nueva entidad de seguridad en la base de datos
        return securityEntityRepository.save(securityEntity);
    }

    @Override
    public List<SecurityEntity> getAll() {
        // Retorna todas las entidades de seguridad
        return securityEntityRepository.findAll();
    }

    @Override
    public SecurityEntity getById(UUID id) {
        // Busca una entidad de seguridad por su ID
        return securityEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Security entity not found with ID: " + id));
    }

    @Override
    public SecurityEntity update(UUID id, SecurityEntity securityEntity) {
        // Actualiza una entidad de seguridad si existe
        SecurityEntity existingEntity = securityEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Security entity not found with ID: " + id));
        existingEntity.setAdmin(securityEntity.getAdmin());
        existingEntity.setDepartment(securityEntity.getDepartment());
        existingEntity.setProvince(securityEntity.getProvince());
        existingEntity.setDistrict(securityEntity.getDistrict());
        existingEntity.setPhone_number(securityEntity.getPhone_number());
        return securityEntityRepository.save(existingEntity);
    }

    @Override
    public void delete(UUID id) {
        // Elimina una entidad de seguridad por su ID
        if (!securityEntityRepository.existsById(id)) {
            throw new RuntimeException("Security entity not found with ID: " + id);
        }
        securityEntityRepository.deleteById(id);
    }

    @Override
    public List<SecurityEntity> getByDepartment(String department) {
        // Retorna todas las entidades de seguridad asociadas a un departamento específico
        return securityEntityRepository.findByDepartment(department);
    }
}
