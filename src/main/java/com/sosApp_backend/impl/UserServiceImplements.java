package com.sosApp_backend.impl;

import com.sosApp_backend.model.User;
import com.sosApp_backend.repository.UserRepository;
import com.sosApp_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImplements implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        // Guarda un nuevo usuario en la base de datos
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        // Retorna todos los usuarios
        return userRepository.findAll();
    }

    @Override
    public User getById(UUID id) {
        // Busca un usuario por su ID
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    @Override
    public User update(UUID id, User user) {
        // Actualiza un usuario si existe
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        existingUser.setFull_name(user.getFull_name());
        existingUser.setUsername(user.getUsername());
        existingUser.setDni(user.getDni());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }

    @Override
    public boolean delete(UUID id) {
        // Verifica si el usuario existe antes de eliminarlo
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User getByUsername(String username) {
        // Busca un usuario por su nombre de usuario
        return userRepository.findByUsername(username);
    }

    @Override
    public User getByDni(String dni) {
        // Busca un usuario por su DNI
        return userRepository.findByDni(dni);
    }
}
