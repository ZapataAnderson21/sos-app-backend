package com.sosApp_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "strike")
public class Strike {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "strike_id")
    private UUID strike_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "security_person_id", referencedColumnName = "security_person_id", nullable = false)
    private SecurityPerson securityPerson;

    @Column(name = "reason", columnDefinition = "TEXT", nullable = false)
    private String reason;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    public Strike() {
    }

    public Strike(UUID strike_id, User user, SecurityPerson securityPerson, String reason, LocalDateTime timestamp) {
        this.strike_id = strike_id;
        this.user = user;
        this.securityPerson = securityPerson;
        this.reason = reason;
        this.timestamp = timestamp;
    }

    public UUID getStrike_id() {
        return strike_id;
    }

    public void setStrike_id(UUID strike_id) {
        this.strike_id = strike_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SecurityPerson getSecurityPerson() {
        return securityPerson;
    }

    public void setSecurityPerson(SecurityPerson securityPerson) {
        this.securityPerson = securityPerson;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
