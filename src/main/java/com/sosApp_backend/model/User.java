package com.sosApp_backend.model;

import com.sosApp_backend.model.emun.Role;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID user_id;

    @Column(name = "full_name", length = 255, nullable = false)
    private String full_name;

    @Column(name = "username", length = 255, nullable = false)
    private String usernmae;

    @Column(name = "dni", length = 50, unique = true, nullable = false)
    private String dni;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public User() { }

    public User(UUID user_id, String full_name, String usernmae, String dni, String password, Role role) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.usernmae = usernmae;
        this.dni = dni;
        this.password = password;
        this.role = role;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUsernmae() {
        return usernmae;
    }

    public void setUsernmae(String usernmae) {
        this.usernmae = usernmae;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

