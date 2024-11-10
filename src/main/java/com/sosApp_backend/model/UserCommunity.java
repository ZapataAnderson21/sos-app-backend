package com.sosApp_backend.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_community", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "community_id"}) // Evita duplicados entre usuario y comunidad
})
public class UserCommunity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_community_id")
    private UUID userCommunityId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    public UserCommunity() {
    }

    public UserCommunity(UUID userCommunityId, User user, Community community) {
        this.userCommunityId = userCommunityId;
        this.user = user;
        this.community = community;
    }

    public UUID getUserCommunityId() {
        return userCommunityId;
    }

    public void setUserCommunityId(UUID userCommunityId) {
        this.userCommunityId = userCommunityId;
    }

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
