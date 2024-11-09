package com.sosApp_backend.impl;

import com.sosApp_backend.model.Community;
import com.sosApp_backend.repository.CommunityRepository;
import com.sosApp_backend.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommunityServiceImplements implements CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Override
    public Community create(Community community) {
        // Guarda una nueva comunidad en la base de datos
        return communityRepository.save(community);
    }

    @Override
    public List<Community> getAll() {
        // Retorna todas las comunidades
        return communityRepository.findAll();
    }

    @Override
    public Community getById(UUID id) {
        // Busca una comunidad por su ID
        return communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found with ID: " + id));
    }

    @Override
    public Community update(UUID id, Community community) {
        // Actualiza una comunidad si existe
        Community existingCommunity = communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found with ID: " + id));
        existingCommunity.setName(community.getName());
        existingCommunity.setCreator(community.getCreator());
        existingCommunity.setDepartment(community.getDepartment());
        existingCommunity.setProvince(community.getProvince());
        existingCommunity.setDistrict(community.getDistrict());
        existingCommunity.setAddress(community.getAddress());
        existingCommunity.setDescription(community.getDescription());
        return communityRepository.save(existingCommunity);
    }

    @Override
    public void delete(UUID id) {
        // Elimina una comunidad por su ID
        if (!communityRepository.existsById(id)) {
            throw new RuntimeException("Community not found with ID: " + id);
        }
        communityRepository.deleteById(id);
    }

    @Override
    public List<Community> getByUserId(UUID userId) {
        // Retorna todas las comunidades creadas por un usuario específico
        return communityRepository.findByUserId(userId);
    }
}
