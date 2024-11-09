package com.sosApp_backend.controller;

import com.sosApp_backend.model.Community;
import com.sosApp_backend.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/communities")
@CrossOrigin("*")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @PostMapping("/create")
    public Community createCommunity(@RequestBody Community community) {
        // Crear una nueva comunidad
        return communityService.create(community);
    }

    @GetMapping("/all")
    public List<Community> getAllCommunities() {
        // Obtener todas las comunidades
        return communityService.getAll();
    }

    @GetMapping("/id/{id}")
    public Community getCommunityById(@PathVariable UUID id) {
        // Obtener una comunidad por su ID
        return communityService.getById(id);
    }

    @PutMapping("/update/{id}")
    public Community updateCommunity(@PathVariable UUID id, @RequestBody Community community) {
        // Actualizar una comunidad por su ID
        return communityService.update(id, community);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCommunity(@PathVariable UUID id) {
        // Eliminar una comunidad por su ID
        communityService.delete(id);
    }

    @GetMapping("/user/{userId}")
    public List<Community> getCommunitiesByUserId(@PathVariable UUID userId) {
        // Obtener todas las comunidades creadas por un usuario específico
        return communityService.getByUserId(userId);
    }
}
