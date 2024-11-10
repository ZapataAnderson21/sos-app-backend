package com.sosApp_backend.controller;

import com.sosApp_backend.model.User;
import com.sosApp_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();

        if (user.getFull_name() == null || user.getFull_name().isEmpty()) {
            response.put("status", false);
            response.put("message", "El nombre completo no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            response.put("status", false);
            response.put("message", "El nombre de usuario no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (user.getDni() == null || user.getDni().isEmpty()) {
            response.put("status", false);
            response.put("message", "El DNI no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            response.put("status", false);
            response.put("message", "La contraseña no puede estar vacía");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User createdUser = userService.create(user);
        response.put("status", true);
        response.put("message", "Usuario creado correctamente");
        response.put("data", createdUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        Map<String, Object> response = new HashMap<>();
        List<User> users = userService.getAll();

        if (users == null || users.isEmpty()) {
            response.put("status", true);
            response.put("message", "No se encontraron usuarios");
            response.put("users", users); // Devolver lista vacía
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", true);
        response.put("message", "Usuarios encontrados");
        response.put("users", users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User user = userService.getById(id);
        if (user == null) {
            response.put("status", false);
            response.put("message", "No se encontró el usuario con el ID proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("data", user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable UUID id, @RequestBody User user) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (user.getFull_name() == null || user.getFull_name().isEmpty()) {
            response.put("status", false);
            response.put("message", "El nombre completo no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            response.put("status", false);
            response.put("message", "El nombre de usuario no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (user.getDni() == null || user.getDni().isEmpty()) {
            response.put("status", false);
            response.put("message", "El DNI no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User updatedUser = userService.update(id, user);
        if (updatedUser == null) {
            response.put("status", false);
            response.put("message", "No se pudo actualizar el usuario. ID no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("message", "Usuario actualizado correctamente");
        response.put("data", updatedUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        boolean deleted = userService.delete(id);
        if (!deleted) {
            response.put("status", false);
            response.put("message", "No se pudo eliminar el usuario. ID no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("message", "Usuario eliminado correctamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Map<String, Object>> getUserByUsername(@PathVariable String username) {
        Map<String, Object> response = new HashMap<>();

        if (username == null || username.isEmpty()) {
            response.put("status", false);
            response.put("message", "El nombre de usuario no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User user = userService.getByUsername(username);
        if (user == null) {
            response.put("status", false);
            response.put("message", "No se encontró el usuario con el nombre de usuario proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("data", user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<Map<String, Object>> getUserByDni(@PathVariable String dni) {
        Map<String, Object> response = new HashMap<>();

        if (dni == null || dni.isEmpty()) {
            response.put("status", false);
            response.put("message", "El DNI no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User user = userService.getByDni(dni);
        if (user == null) {
            response.put("status", false);
            response.put("message", "No se encontró el usuario con el DNI proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("data", user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
