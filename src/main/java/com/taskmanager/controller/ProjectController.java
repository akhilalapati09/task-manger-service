package com.taskmanager.controller;

import com.taskmanager.model.Project;
import com.taskmanager.service.ProjectService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectController {
    
    @Inject
    ProjectService projectService;
    
    @GET
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }
    
    @GET
    @Path("/{id}")
    public Response getProjectById(@PathParam("id") Long id) {
        Project project = projectService.getProjectById(id);
        if (project != null) {
            return Response.ok(project).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @POST
    public Project createProject(Project project) {
        return projectService.createProject(project);
    }
    
    @PUT
    @Path("/{id}")
    public Response updateProject(@PathParam("id") Long id, Project project) {
        Project updated = projectService.updateProject(id, project);
        if (updated != null) {
            return Response.ok(updated).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteProject(@PathParam("id") Long id) {
        boolean deleted = projectService.deleteProject(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
