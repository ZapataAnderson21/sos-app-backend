package com.sosApp_backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "alert")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "alert_id")
    private UUID alert_id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "location", length = 100, nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "community_id", referencedColumnName = "community_id", nullable = false)
    private Community community;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    public Alert() {
    }

    public Alert(UUID alert_id, String title, String description, String location, User user, Community community, LocalDateTime created_at) {
        this.alert_id = alert_id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.user = user;
        this.community = community;
        this.created_at = created_at;
    }

    public UUID getAlert_id() {
        return alert_id;
    }

    public void setAlert_id(UUID alert_id) {
        this.alert_id = alert_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}

