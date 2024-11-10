package com.sosApp_backend.controller;

import com.sosApp_backend.model.Community;
import com.sosApp_backend.model.User;
import com.sosApp_backend.model.UserCommunity;
import com.sosApp_backend.service.UserCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user-community")
@CrossOrigin("*")
public class UserCommunityController {

    @Autowired
    private UserCommunityService userCommunityService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addUserToCommunity(@RequestBody UserCommunityRequest userCommunityReq) {
        Map<String, Object> response = new HashMap<>();

        if (userCommunityReq.getUser() == null || userCommunityReq.getUser().getUser_id() == null) {
            response.put("status", false);
            response.put("message", "El usuario no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (userCommunityReq.getCommunity() == null || userCommunityReq.getCommunity().getCommunity_id() == null) {
            response.put("status", false);
            response.put("message", "La comunidad no puede estar vacía");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String resultMessage = userCommunityService.addUserToCommunity(userCommunityReq.getUser(), userCommunityReq.getCommunity());
        response.put("status", true);
        response.put("message", resultMessage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserCommunity>> getAllUserCommunities() {
        List<UserCommunity> userCommunities = userCommunityService.getAllUserCommunities();
        return new ResponseEntity<>(userCommunities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserCommunityById(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        UserCommunity userCommunity = userCommunityService.getUserCommunityById(id);
        if (userCommunity == null) {
            response.put("status", false);
            response.put("message", "No se encontró la relación usuario-comunidad con el ID proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("data", userCommunity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUserFromCommunity(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String resultMessage = userCommunityService.deleteUserFromCommunity(id);
        response.put("status", true);
        response.put("message", resultMessage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getCommunitiesByUser(@PathVariable UUID userId) {
        Map<String, Object> response = new HashMap<>();

        if (userId == null) {
            response.put("status", false);
            response.put("message", "El ID del usuario no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        List<UserCommunity> userCommunities = userCommunityService.getCommunitiesByUser(new User(userId, null, null, null, null, null));
        if (userCommunities == null || userCommunities.isEmpty()) {
            response.put("status", false);
            response.put("message", "No se encontraron comunidades asociadas al usuario con el ID proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("communities", userCommunities);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

class UserCommunityRequest {
    private User user;
    private Community community;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}
