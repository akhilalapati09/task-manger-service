package com.taskmanager.service;

import com.taskmanager.controller.TaskController.TaskStats;
import com.taskmanager.model.Task;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TaskService {
    
    public List<Task> getAllTasks() {
        return Task.listAll();
    }
    
    public Task getTaskById(Long id) {
        return Task.findById(id);
    }
    
    @Transactional
    public Task createTask(Task task) {
        task.persist();
        return task;
    }
    
    @Transactional
    public Task updateTask(Long id, Task taskDetails) {
        Task task = Task.findById(id);
        if (task != null) {
            task.title = taskDetails.title;
            task.description = taskDetails.description;
            task.priority = taskDetails.priority;
            task.status = taskDetails.status;
            task.dueDate = taskDetails.dueDate;
            task.assignedTo = taskDetails.assignedTo;
            task.project = taskDetails.project;
            task.persist();
        }
        return task;
    }
    
    @Transactional
    public boolean deleteTask(Long id) {
        return Task.deleteById(id);
    }
    
    @Transactional
    public Task updateTaskStatus(Long id, Task.Status status) {
        Task task = Task.findById(id);
        if (task != null) {
            task.status = status;
            task.persist();
        }
        return task;
    }
    
    public TaskStats getTaskStats() {
        long total = Task.count();
        long todo = Task.countByStatus(Task.Status.TODO);
        long inProgress = Task.countByStatus(Task.Status.IN_PROGRESS);
        long completed = Task.countByStatus(Task.Status.COMPLETED);
        
        return new TaskStats(total, todo, inProgress, completed);
    }
}
