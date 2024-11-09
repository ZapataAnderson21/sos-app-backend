package com.sosApp_backend.service;

import com.sosApp_backend.model.Alert;
import java.util.List;
import java.util.UUID;

public interface AlertService {
    Alert create(Alert alert);

    List<Alert> getAll();

    Alert getById(UUID id);

    Alert update(UUID id, Alert alert);

    void delete(UUID id);

    List<Alert> getByUserId(UUID userId);

    List<Alert> getByCommunityId(UUID communityId);
}
