package com.sosApp_backend.controller;

import com.sosApp_backend.model.User;
import com.sosApp_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        // Crear un nuevo usuario
        return userService.create(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        // Obtener todos los usuarios
        return userService.getAll();
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable UUID id) {
        // Obtener un usuario por su ID
        return userService.getById(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user) {
        // Actualizar un usuario por su ID
        return userService.update(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable UUID id) {
        // Eliminar un usuario por su ID
        userService.delete(id);
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        // Obtener un usuario por su nombre de usuario
        return userService.getByUsername(username);
    }

    @GetMapping("/dni/{dni}")
    public User getUserByDni(@PathVariable String dni) {
        // Obtener un usuario por su DNI
        return userService.getByDni(dni);
    }
}
