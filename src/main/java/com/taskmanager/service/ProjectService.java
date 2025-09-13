package com.taskmanager.service;

import com.taskmanager.model.Project;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProjectService {
    
    public List<Project> getAllProjects() {
        return Project.listAll();
    }
    
    public Project getProjectById(Long id) {
        return Project.findById(id);
    }
    
    @Transactional
    public Project createProject(Project project) {
        project.persist();
        return project;
    }
    
    @Transactional
    public Project updateProject(Long id, Project projectDetails) {
        Project project = Project.findById(id);
        if (project != null) {
            project.name = projectDetails.name;
            project.description = projectDetails.description;
            project.persist();
        }
        return project;
    }
    
    @Transactional
    public boolean deleteProject(Long id) {
        return Project.deleteById(id);
    }
}
