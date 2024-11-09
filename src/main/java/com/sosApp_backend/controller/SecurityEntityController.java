package com.sosApp_backend.controller;

import com.sosApp_backend.model.SecurityEntity;
import com.sosApp_backend.service.SecurityEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/security-entities")
@CrossOrigin("*")
public class SecurityEntityController {

    @Autowired
    private SecurityEntityService securityEntityService;

    @PostMapping("/create")
    public SecurityEntity createSecurityEntity(@RequestBody SecurityEntity securityEntity) {
        // Crear una nueva entidad de seguridad
        return securityEntityService.create(securityEntity);
    }

    @GetMapping("/all")
    public List<SecurityEntity> getAllSecurityEntities() {
        // Obtener todas las entidades de seguridad
        return securityEntityService.getAll();
    }

    @GetMapping("/id/{id}")
    public SecurityEntity getSecurityEntityById(@PathVariable UUID id) {
        // Obtener una entidad de seguridad por su ID
        return securityEntityService.getById(id);
    }

    @PutMapping("/update/{id}")
    public SecurityEntity updateSecurityEntity(@PathVariable UUID id, @RequestBody SecurityEntity securityEntity) {
        // Actualizar una entidad de seguridad por su ID
        return securityEntityService.update(id, securityEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSecurityEntity(@PathVariable UUID id) {
        // Eliminar una entidad de seguridad por su ID
        securityEntityService.delete(id);
    }

    @GetMapping("/department/{department}")
    public List<SecurityEntity> getSecurityEntitiesByDepartment(@PathVariable String department) {
        // Obtener todas las entidades de seguridad asociadas a un departamento específico
        return securityEntityService.getByDepartment(department);
    }
}
