package com.taskmanager.repository;

import com.taskmanager.model.Task;
import com.taskmanager.model.Task.Status;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class TaskRepository implements PanacheRepository<Task> {
    
    public List<Task> findByProjectId(Long projectId) {
        return list("project.id", projectId);
    }
    
    public List<Task> findOverdueTasks() {
        return list("dueDate < ?1 and status != ?2", LocalDate.now(), Status.COMPLETED);
    }
    
    public List<Task> findByStatus(Status status) {
        return list("status", status);
    }
    
    public List<Task> findByAssignedTo(String email) {
        return list("assignedTo", email);
    }
    
    public long countByStatus(Status status) {
        return count("status", status);
    }
}
