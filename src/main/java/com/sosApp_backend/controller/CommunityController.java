package com.sosApp_backend.controller;

import com.sosApp_backend.model.Community;
import com.sosApp_backend.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/community")
@CrossOrigin("*")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createCommunity(@RequestBody Community community) {
        Map<String, Object> response = new HashMap<>();

        if (community.getName() == null || community.getName().isEmpty()) {
            response.put("status", false);
            response.put("message", "El nombre de la comunidad no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (community.getCreator() == null || community.getCreator().getUser_id() == null) {
            response.put("status", false);
            response.put("message", "El creador de la comunidad no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (community.getDepartment() == null || community.getDepartment().isEmpty()) {
            response.put("status", false);
            response.put("message", "El departamento no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (community.getProvince() == null || community.getProvince().isEmpty()) {
            response.put("status", false);
            response.put("message", "La provincia no puede estar vacía");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (community.getDistrict() == null || community.getDistrict().isEmpty()) {
            response.put("status", false);
            response.put("message", "El distrito no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Community createdCommunity = communityService.create(community);
        response.put("status", true);
        response.put("message", "Comunidad creada correctamente");
        response.put("community", createdCommunity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Community>> getAllCommunities() {
        List<Community> communities = communityService.getAll();
        return new ResponseEntity<>(communities, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Map<String, Object>> getCommunityById(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Community community = communityService.getById(id);
        if (community == null) {
            response.put("status", false);
            response.put("message", "No se encontró la comunidad con el ID proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("community", community);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateCommunity(@PathVariable UUID id, @RequestBody Community community) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (community.getName() == null || community.getName().isEmpty()) {
            response.put("status", false);
            response.put("message", "El nombre de la comunidad no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Community updatedCommunity = communityService.update(id, community);
        if (updatedCommunity == null) {
            response.put("status", false);
            response.put("message", "No se pudo actualizar la comunidad. ID no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("message", "Comunidad actualizada correctamente");
        response.put("community", updatedCommunity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteCommunity(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        boolean deleted = communityService.delete(id);
        if (!deleted) {
            response.put("status", false);
            response.put("message", "No se pudo eliminar la comunidad. ID no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("message", "Comunidad eliminada correctamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getCommunitiesByUserId(@PathVariable UUID userId) {
        Map<String, Object> response = new HashMap<>();

        if (userId == null) {
            response.put("status", false);
            response.put("message", "El ID del usuario no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        List<Community> communities = communityService.getByUserId(userId);
        if (communities == null || communities.isEmpty()) {
            response.put("status", false);
            response.put("message", "No se encontraron comunidades creadas por el usuario con el ID proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("communities", communities);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
