package br.com.fiap.hotel.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name = "guests")
public class Guest {
    @Id
    private String id;

    @Column(name = "full_name", nullable = false, length = 120)
    private String fullName;

    @Column(nullable = false, length = 30, unique = true)
    private String document;

    @Column(nullable = false, length = 120, unique = true)
    private String email;

    @Column(length = 30)
    private String phone;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}