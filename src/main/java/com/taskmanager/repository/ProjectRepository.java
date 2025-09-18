package com.taskmanager.repository;

import com.taskmanager.model.Project;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjectRepository implements PanacheRepository<Project> {
    
    public Project findByName(String name) {
        return find("name", name).firstResult();
    }
    
    public boolean existsByName(String name) {
        return find("name", name).count() > 0;
    }
}
