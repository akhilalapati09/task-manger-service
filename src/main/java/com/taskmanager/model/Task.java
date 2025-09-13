package com.taskmanager.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task extends PanacheEntity {
    
    @NotBlank
    public String title;
    
    public String description;
    
    @Enumerated(EnumType.STRING)
    public Priority priority = Priority.MEDIUM;
    
    @Enumerated(EnumType.STRING)
    public Status status = Status.TODO;
    
    public LocalDate dueDate;
    
    public String assignedTo;
    
    @ManyToOne
    @JoinColumn(name = "project_id")
    public Project project;
    
    public LocalDateTime createdAt = LocalDateTime.now();
    
    public LocalDateTime updatedAt = LocalDateTime.now();
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    // Static finder methods (Panache style)
    public static long countByStatus(Status status) {
        return count("status", status);
    }
    
    public static java.util.List<Task> findByStatus(Status status) {
        return list("status", status);
    }
    
    public static java.util.List<Task> findByAssignedTo(String assignedTo) {
        return list("assignedTo", assignedTo);
    }
    
    public enum Priority {
        LOW, MEDIUM, HIGH
    }
    
    public enum Status {
        TODO, IN_PROGRESS, COMPLETED
    }
}
