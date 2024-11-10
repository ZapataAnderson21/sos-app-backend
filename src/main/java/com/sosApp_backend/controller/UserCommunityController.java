package com.sosApp_backend.controller;

import com.sosApp_backend.model.Community;
import com.sosApp_backend.model.User;
import com.sosApp_backend.model.UserCommunity;
import com.sosApp_backend.service.UserCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-communities")
public class UserCommunityController {

    @Autowired
    private UserCommunityService userCommunityService;

    @PostMapping("/add")
    public String addUserToCommunity(@RequestBody UserCommunityRequest request) {
        return userCommunityService.addUserToCommunity(request.getUser(), request.getCommunity());
    }

    @GetMapping
    public List<UserCommunity> getAllUserCommunities() {
        return userCommunityService.getAllUserCommunities();
    }

    @GetMapping("/{id}")
    public UserCommunity getUserCommunityById(@PathVariable UUID id) {
        return userCommunityService.getUserCommunityById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUserFromCommunity(@PathVariable UUID id) {
        return userCommunityService.deleteUserFromCommunity(id);
    }

    @GetMapping("/user/{userId}")
    public List<UserCommunity> getCommunitiesByUser(@PathVariable UUID userId) {
        User user = new User(); // Obtener usuario real de un servicio (no implementado aquí)
        user.setUser_id(userId);
        return userCommunityService.getCommunitiesByUser(user);
    }
}

class UserCommunityRequest {
    private User user;
    private Community community;

    // Getters y setters
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
