package com.sosApp_backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "security_person")
public class SecurityPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID security_person_id;

    @ManyToOne
    @JoinColumn(name = "security_entity_id", referencedColumnName = "security_entity_id", nullable = false)
    private SecurityEntity securityEntity;

    @Column(name = "full_name", length = 255, nullable = false)
    private String full_name;

    @Column(name = "dni", length = 50, nullable = false)
    private String dni;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    public SecurityPerson() {
    }

    public SecurityPerson(UUID security_person_id, SecurityEntity securityEntity, String full_name, String dni, String password) {
        this.security_person_id = security_person_id;
        this.securityEntity = securityEntity;
        this.full_name = full_name;
        this.dni = dni;
        this.password = password;
    }

    public UUID getSecurity_person_id() {
        return security_person_id;
    }

    public void setSecurity_person_id(UUID security_person_id) {
        this.security_person_id = security_person_id;
    }

    public SecurityEntity getSecurityEntity() {
        return securityEntity;
    }

    public void setSecurityEntity(SecurityEntity securityEntity) {
        this.securityEntity = securityEntity;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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
}

