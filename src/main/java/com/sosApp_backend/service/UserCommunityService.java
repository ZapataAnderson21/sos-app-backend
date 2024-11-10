package com.sosApp_backend.service;

import com.sosApp_backend.model.Community;
import com.sosApp_backend.model.User;
import com.sosApp_backend.model.UserCommunity;

import java.util.List;
import java.util.UUID;

public interface UserCommunityService {
    String addUserToCommunity(User user, Community community);

    List<UserCommunity> getAllUserCommunities();

    UserCommunity getUserCommunityById(UUID id);

    String deleteUserFromCommunity(UUID id);

    List<UserCommunity> getCommunitiesByUser(User user);
}
