package com.sosApp_backend.controller;

import com.sosApp_backend.model.SecurityPerson;
import com.sosApp_backend.service.SecurityPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/security-persons")
@CrossOrigin("*")
public class SecurityPersonController {

    @Autowired
    private SecurityPersonService securityPersonService;

    @PostMapping("/create")
    public SecurityPerson createSecurityPerson(@RequestBody SecurityPerson securityPerson) {
        // Crear una nueva persona de seguridad
        return securityPersonService.create(securityPerson);
    }

    @GetMapping("/all")
    public List<SecurityPerson> getAllSecurityPersons() {
        // Obtener todas las personas de seguridad
        return securityPersonService.getAll();
    }

    @GetMapping("/id/{id}")
    public SecurityPerson getSecurityPersonById(@PathVariable UUID id) {
        // Obtener una persona de seguridad por su ID
        return securityPersonService.getById(id);
    }

    @PutMapping("/update/{id}")
    public SecurityPerson updateSecurityPerson(@PathVariable UUID id, @RequestBody SecurityPerson securityPerson) {
        // Actualizar una persona de seguridad por su ID
        return securityPersonService.update(id, securityPerson);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSecurityPerson(@PathVariable UUID id) {
        // Eliminar una persona de seguridad por su ID
        securityPersonService.delete(id);
    }

    @GetMapping("/entity/{securityEntityId}")
    public List<SecurityPerson> getSecurityPersonsByEntityId(@PathVariable UUID securityEntityId) {
        // Obtener todas las personas de seguridad asociadas a una entidad de seguridad específica
        return securityPersonService.getBySecurityEntityId(securityEntityId);
    }
}
