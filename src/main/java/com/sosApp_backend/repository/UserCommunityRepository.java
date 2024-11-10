package com.sosApp_backend.repository;

import com.sosApp_backend.model.Community;
import com.sosApp_backend.model.User;
import com.sosApp_backend.model.UserCommunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserCommunityRepository extends JpaRepository<UserCommunity, UUID> {

    @Query(value = "SELECT COUNT(*) FROM user_community WHERE user_id = :userId", nativeQuery = true)
    long countByUserId(UUID userId);

    @Query(value = "SELECT * FROM user_community WHERE user_id = :userId", nativeQuery = true)
    List<UserCommunity> findByUserId(UUID userId);

    @Query(value = "SELECT * FROM user_community WHERE community_id = :communityId", nativeQuery = true)
    List<UserCommunity> findByCommunityId(UUID communityId);
}
