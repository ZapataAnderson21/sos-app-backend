package com.sosApp_backend.controller;

import com.sosApp_backend.model.SecurityEntity;
import com.sosApp_backend.service.SecurityEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/security-entity")
@CrossOrigin("*")
public class SecurityEntityController {

    @Autowired
    private SecurityEntityService securityEntityService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createSecurityEntity(@RequestBody SecurityEntity securityEntity) {
        Map<String, Object> response = new HashMap<>();

        if (securityEntity.getDepartment() == null || securityEntity.getDepartment().isEmpty()) {
            response.put("status", false);
            response.put("message", "El departamento no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (securityEntity.getProvince() == null || securityEntity.getProvince().isEmpty()) {
            response.put("status", false);
            response.put("message", "La provincia no puede estar vacía");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (securityEntity.getDistrict() == null || securityEntity.getDistrict().isEmpty()) {
            response.put("status", false);
            response.put("message", "El distrito no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (securityEntity.getPhone_number() == null || securityEntity.getPhone_number().isEmpty()) {
            response.put("status", false);
            response.put("message", "El número de teléfono no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        SecurityEntity createdEntity = securityEntityService.create(securityEntity);
        response.put("status", true);
        response.put("message", "Entidad de seguridad creada correctamente");
        response.put("data", createdEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SecurityEntity>> getAllSecurityEntities() {
        List<SecurityEntity> entities = securityEntityService.getAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Map<String, Object>> getSecurityEntityById(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            SecurityEntity entity = securityEntityService.getById(id);
            response.put("status", true);
            response.put("message", "Entidad de seguridad encontrada");
            response.put("data", entity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            response.put("status", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateSecurityEntity(@PathVariable UUID id, @RequestBody SecurityEntity securityEntity) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            SecurityEntity updatedEntity = securityEntityService.update(id, securityEntity);
            response.put("status", true);
            response.put("message", "Entidad de seguridad actualizada correctamente");
            response.put("data", updatedEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            response.put("status", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteSecurityEntity(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            securityEntityService.delete(id);
            response.put("status", true);
            response.put("message", "Entidad de seguridad eliminada correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            response.put("status", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<Map<String, Object>> getSecurityEntitiesByDepartment(@PathVariable String department) {
        Map<String, Object> response = new HashMap<>();

        if (department == null || department.isEmpty()) {
            response.put("status", false);
            response.put("message", "El nombre del departamento no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        List<SecurityEntity> entities = securityEntityService.getByDepartment(department);
        if (entities == null || entities.isEmpty()) {
            response.put("status", false);
            response.put("message", "No se encontraron entidades de seguridad para el departamento proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("entities", entities);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
