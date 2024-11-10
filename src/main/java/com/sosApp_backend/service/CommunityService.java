package com.sosApp_backend.service;

import com.sosApp_backend.model.Community;
import java.util.List;
import java.util.UUID;

public interface CommunityService {
    Community create(Community community);
    List<Community> getAll();
    Community getById(UUID id);
    Community update(UUID id, Community community);
    boolean delete(UUID id);
    List<Community> getByUserId(UUID userId);
}
