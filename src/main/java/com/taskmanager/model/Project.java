package com.taskmanager.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project extends PanacheEntity {
    
    @NotBlank
    public String name;
    
    public String description;
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    public List<Task> tasks;
    
    public LocalDateTime createdAt = LocalDateTime.now();
}
