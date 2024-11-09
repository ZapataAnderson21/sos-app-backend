package com.sosApp_backend.service;

import com.sosApp_backend.model.SecurityEntity;
import java.util.List;
import java.util.UUID;

public interface SecurityEntityService {
    SecurityEntity create(SecurityEntity securityEntity);
    List<SecurityEntity> getAll();
    SecurityEntity getById(UUID id);
    SecurityEntity update(UUID id, SecurityEntity securityEntity);
    void delete(UUID id);
    List<SecurityEntity> getByDepartment(String department);
}
