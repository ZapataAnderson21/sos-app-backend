package com.sosApp_backend.service;

import com.sosApp_backend.model.Strike;
import com.sosApp_backend.model.User;

import java.util.List;

public interface StrikeService {

    String assignStrikeToUser(Strike strike);

    List<Strike> getStrikesByUser(User user);

    int getStrikeCountByUser(User user);
}
