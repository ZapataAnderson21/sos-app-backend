package com.sosApp_backend.service;

import com.sosApp_backend.model.SecurityPerson;
import java.util.List;
import java.util.UUID;

public interface SecurityPersonService {
    SecurityPerson create(SecurityPerson securityPerson);
    List<SecurityPerson> getAll();
    SecurityPerson getById(UUID id);
    SecurityPerson update(UUID id, SecurityPerson securityPerson);
    void delete(UUID id);
    List<SecurityPerson> getBySecurityEntityId(UUID securityEntityId);
}
