package com.infosys.carbonfootprint.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", unique = true, length = 50)
    private String username;

    @Column(name = "role", nullable = false, length = 20)
    private String role;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "first_login", nullable = false)
    private Boolean firstLogin;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PersonalDetails personalDetails;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserAddress userAddress;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserGovernmentDocument governmentDocument;

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public UserGovernmentDocument getGovernmentDocument() {
        return governmentDocument;
    }

    public void setGovernmentDocument(UserGovernmentDocument governmentDocument) {
        this.governmentDocument = governmentDocument;
    }
}
