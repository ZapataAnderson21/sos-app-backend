package com.sosApp_backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "security_entity")
public class SecurityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID security_entity_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User admin;

    @Column(name = "department", length = 50, nullable = false)
    private String department;

    @Column(name = "province", length = 50, nullable = false)
    private String province;

    @Column(name = "district", length = 50, nullable = false)
    private String district;

    @Column(name = "phone_number", length = 50, nullable = false)
    private String phone_number;

    public SecurityEntity() {
    }

    public SecurityEntity(UUID security_entity_id, User admin, String department, String province, String district, String phone_number) {
        this.security_entity_id = security_entity_id;
        this.admin = admin;
        this.department = department;
        this.province = province;
        this.district = district;
        this.phone_number = phone_number;
    }

    public UUID getSecurity_entity_id() {
        return security_entity_id;
    }

    public void setSecurity_entity_id(UUID security_entity_id) {
        this.security_entity_id = security_entity_id;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}

