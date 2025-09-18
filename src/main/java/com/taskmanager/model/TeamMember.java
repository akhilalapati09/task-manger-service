package com.taskmanager.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "team_members")
public class TeamMember extends PanacheEntity {
    
    @Column(nullable = false)
    public String name;
    
    @Column(unique = true, nullable = false)
    public String email;
    
    @Column(nullable = false)
    public String role;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    public LocalDateTime createdAt;
    
    // Default constructor required by Hibernate
    public TeamMember() {
    }
    
    public TeamMember(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }
    
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
