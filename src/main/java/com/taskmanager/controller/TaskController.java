package com.taskmanager.controller;

import com.taskmanager.model.Task;
import com.taskmanager.service.TaskService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskController {
    
    @Inject
    TaskService taskService;
    
    @GET
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
    
    @GET
    @Path("/{id}")
    public Response getTaskById(@PathParam("id") Long id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            return Response.ok(task).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @POST
    public Task createTask(Task task) {
        return taskService.createTask(task);
    }
    
    @PUT
    @Path("/{id}")
    public Response updateTask(@PathParam("id") Long id, Task task) {
        Task updated = taskService.updateTask(id, task);
        if (updated != null) {
            return Response.ok(updated).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") Long id) {
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @PATCH
    @Path("/{id}/status")
    public Response updateTaskStatus(@PathParam("id") Long id, @QueryParam("status") String status) {
        Task updated = taskService.updateTaskStatus(id, Task.Status.valueOf(status));
        if (updated != null) {
            return Response.ok(updated).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("/stats")
    public TaskStats getTaskStats() {
        return taskService.getTaskStats();
    }
    
    public static class TaskStats {
        public long total;
        public long todo;
        public long inProgress;
        public long completed;
        
        public TaskStats(long total, long todo, long inProgress, long completed) {
            this.total = total;
            this.todo = todo;
            this.inProgress = inProgress;
            this.completed = completed;
        }
    }
}
