package com.sosApp_backend.controller;

import com.sosApp_backend.model.SecurityPerson;
import com.sosApp_backend.service.SecurityPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/security-person")
@CrossOrigin("*")
public class SecurityPersonController {

    @Autowired
    private SecurityPersonService securityPersonService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createSecurityPerson(@RequestBody SecurityPerson securityPerson) {
        Map<String, Object> response = new HashMap<>();

        if (securityPerson.getFull_name() == null || securityPerson.getFull_name().isEmpty()) {
            response.put("status", false);
            response.put("message", "El nombre completo no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (securityPerson.getDni() == null || securityPerson.getDni().isEmpty()) {
            response.put("status", false);
            response.put("message", "El DNI no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (securityPerson.getPassword() == null || securityPerson.getPassword().isEmpty()) {
            response.put("status", false);
            response.put("message", "La contraseña no puede estar vacía");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (securityPerson.getSecurityEntity() == null || securityPerson.getSecurityEntity().getSecurity_entity_id() == null) {
            response.put("status", false);
            response.put("message", "La entidad de seguridad asociada no puede estar vacía");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        SecurityPerson createdPerson = securityPersonService.create(securityPerson);
        response.put("status", true);
        response.put("message", "Persona de seguridad creada correctamente");
        response.put("data", createdPerson);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SecurityPerson>> getAllSecurityPersons() {
        List<SecurityPerson> persons = securityPersonService.getAll();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Map<String, Object>> getSecurityPersonById(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            SecurityPerson person = securityPersonService.getById(id);
            response.put("status", true);
            response.put("message", "Persona de seguridad encontrada");
            response.put("data", person);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            response.put("status", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateSecurityPerson(@PathVariable UUID id, @RequestBody SecurityPerson securityPerson) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (securityPerson.getFull_name() == null || securityPerson.getFull_name().isEmpty()) {
            response.put("status", false);
            response.put("message", "El nombre completo no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (securityPerson.getDni() == null || securityPerson.getDni().isEmpty()) {
            response.put("status", false);
            response.put("message", "El DNI no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (securityPerson.getPassword() == null || securityPerson.getPassword().isEmpty()) {
            response.put("status", false);
            response.put("message", "La contraseña no puede estar vacía");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            SecurityPerson updatedPerson = securityPersonService.update(id, securityPerson);
            response.put("status", true);
            response.put("message", "Persona de seguridad actualizada correctamente");
            response.put("data", updatedPerson);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            response.put("status", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteSecurityPerson(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            securityPersonService.delete(id);
            response.put("status", true);
            response.put("message", "Persona de seguridad eliminada correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            response.put("status", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/entity/{securityEntityId}")
    public ResponseEntity<Map<String, Object>> getSecurityPersonsByEntityId(@PathVariable UUID securityEntityId) {
        Map<String, Object> response = new HashMap<>();

        if (securityEntityId == null) {
            response.put("status", false);
            response.put("message", "El ID de la entidad de seguridad no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        List<SecurityPerson> persons = securityPersonService.getBySecurityEntityId(securityEntityId);
        if (persons == null || persons.isEmpty()) {
            response.put("status", false);
            response.put("message", "No se encontraron personas de seguridad para la entidad proporcionada");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("persons", persons);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
