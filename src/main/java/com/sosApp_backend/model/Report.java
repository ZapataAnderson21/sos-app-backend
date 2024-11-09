package com.sosApp_backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID report_id;

    @ManyToOne
    @JoinColumn(name = "community_id", referencedColumnName = "community_id", nullable = false)
    private Community community;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    public Report() {
    }

    public Report(UUID report_id, Community community, User user, String content, LocalDateTime created_at) {
        this.report_id = report_id;
        this.community = community;
        this.user = user;
        this.content = content;
        this.created_at = created_at;
    }

    public UUID getReport_id() {
        return report_id;
    }

    public void setReport_id(UUID report_id) {
        this.report_id = report_id;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}

