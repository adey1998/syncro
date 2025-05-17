package com.syncro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syncro.model.Task;
import com.syncro.model.User;
import com.syncro.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService (TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksByUser(User user) {
        return taskRepository.findByUser(user);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public boolean deleteTask(Long id, User user) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if(taskOptional.isPresent()) {
            Task task = taskOptional.get();
            if(task.getUser().getId().equals(user.getId())) {
                taskRepository.delete(task);
                return true;
            }
        }
        return false;
    }
}
