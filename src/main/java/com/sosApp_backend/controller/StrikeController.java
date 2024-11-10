package com.sosApp_backend.controller;

import com.sosApp_backend.model.Strike;
import com.sosApp_backend.model.User;
import com.sosApp_backend.service.StrikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/strike")
@CrossOrigin("*")
public class StrikeController {

    @Autowired
    private StrikeService strikeService;

    @PostMapping("/assign")
    public ResponseEntity<Map<String, Object>> assignStrikeToUser(@RequestBody Strike strike) {
        Map<String, Object> response = new HashMap<>();

        if (strike.getUser() == null || strike.getUser().getUser_id() == null) {
            response.put("status", false);
            response.put("message", "El usuario al que se asignará el strike no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (strike.getSecurityPerson() == null || strike.getSecurityPerson().getSecurity_person_id() == null) {
            response.put("status", false);
            response.put("message", "La persona de seguridad que asigna el strike no puede estar vacía");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (strike.getReason() == null || strike.getReason().isEmpty()) {
            response.put("status", false);
            response.put("message", "La razón del strike no puede estar vacía");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String resultMessage = strikeService.assignStrikeToUser(strike);
        response.put("status", true);
        response.put("message", resultMessage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getStrikesByUser(@PathVariable UUID userId) {
        Map<String, Object> response = new HashMap<>();

        if (userId == null) {
            response.put("status", false);
            response.put("message", "El ID del usuario no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        List<Strike> strikes = strikeService.getStrikesByUser(new User(userId, null, null, null, null, null));
        if (strikes == null || strikes.isEmpty()) {
            response.put("status", false);
            response.put("message", "No se encontraron strikes para el usuario con el ID proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("strikes", strikes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/count")
    public ResponseEntity<Map<String, Object>> getStrikeCountByUser(@PathVariable UUID userId) {
        Map<String, Object> response = new HashMap<>();

        if (userId == null) {
            response.put("status", false);
            response.put("message", "El ID del usuario no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        int strikeCount = strikeService.getStrikeCountByUser(new User(userId, null, null, null, null, null));
        response.put("status", true);
        response.put("strikeCount", strikeCount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
