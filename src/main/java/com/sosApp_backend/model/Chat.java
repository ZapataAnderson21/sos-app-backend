package com.sosApp_backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "chat_id")
    private UUID chat_id;

    @ManyToOne
    @JoinColumn(name = "community_id", referencedColumnName = "community_id", nullable = false)
    private Community community;

    public Chat() {
    }

    public Chat(UUID chat_id, Community community) {
        this.chat_id = chat_id;
        this.community = community;
    }

    public UUID getChat_id() {
        return chat_id;
    }

    public void setChat_id(UUID chat_id) {
        this.chat_id = chat_id;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}

