package com.sosApp_backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "community")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "community_id")
    private UUID community_id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false) // Creator of the community
    private User creator;

    @Column(name = "department", length = 50, nullable = false)
    private String department;

    @Column(name = "province", length = 50, nullable = false)
    private String province;

    @Column(name = "district", length = 50, nullable = false)
    private String district;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    public Community() {
    }

    public Community(UUID community_id, String name, User creator, String department, String province, String district, String address, String description) {
        this.community_id = community_id;
        this.name = name;
        this.creator = creator;
        this.department = department;
        this.province = province;
        this.district = district;
        this.address = address;
        this.description = description;
    }

    public UUID getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(UUID community_id) {
        this.community_id = community_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
