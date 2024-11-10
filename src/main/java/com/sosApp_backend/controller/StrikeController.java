package com.sosApp_backend.controller;

import com.sosApp_backend.model.Strike;
import com.sosApp_backend.model.User;
import com.sosApp_backend.service.StrikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/strikes")
public class StrikeController {

    @Autowired
    private StrikeService strikeService;

    @PostMapping("/assign")
    public String assignStrikeToUser(@RequestBody Strike strike) {
        return strikeService.assignStrikeToUser(strike);
    }

    @GetMapping("/user/{userId}")
    public List<Strike> getStrikesByUser(@PathVariable UUID userId) {
        User user = new User();
        user.setUser_id(userId);
        return strikeService.getStrikesByUser(user);
    }

    @GetMapping("/user/{userId}/count")
    public int getStrikeCountByUser(@PathVariable UUID userId) {
        User user = new User();
        user.setUser_id(userId);
        return strikeService.getStrikeCountByUser(user);
    }
}
