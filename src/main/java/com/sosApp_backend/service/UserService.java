package com.sosApp_backend.service;

import com.sosApp_backend.model.User;
import java.util.List;
import java.util.UUID;

public interface UserService {
    User create(User user);
    List<User> getAll();
    User getById(UUID id);
    User update(UUID id, User user);
    boolean delete(UUID id);
    User getByUsername(String username);
    User getByDni(String dni);
}
