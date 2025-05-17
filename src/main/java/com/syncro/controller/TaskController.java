package com.syncro.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syncro.dtos.TaskRequestDTO;
import com.syncro.dtos.TaskResponseDTO;
import com.syncro.model.Task;
import com.syncro.service.TaskService;
import com.syncro.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    // get all tasks of logged-in user
    @GetMapping
    @PreAuthorize("isAuthenticated()") // only authenticated users (users with valid JWT)
    public ResponseEntity<?> getTasks(Principal principal) {
        // get logged in user
        var user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Authenticated user not found"));

        // get tasks from db
        List<Task> tasks = taskService.getTasksByUser(user);

        //create ResponseDTO to send back to user
        List<TaskResponseDTO> taskDTOs = tasks.stream()
                .map(t -> new TaskResponseDTO(t.getId(), t.getTitle(), t.getDescription()))
                .toList();
        return ResponseEntity.ok(taskDTOs);
    }

    // create new task
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskRequestDTO taskRequest, Principal principal) {
        // get logged in user
        var user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Authenticated user not found"));

        // create task obj to insert into db
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setUser(user);

        // create in db
        Task createdTask = taskService.createTask(task);

        // create responseDTO to send back to user
        TaskResponseDTO responseDTO = new TaskResponseDTO(
                createdTask.getId(),
                createdTask.getTitle(),
                createdTask.getDescription());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // delete task by ID (only if it belongs to logged-in user)
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteTask(@PathVariable Long id, Principal principal) {
        // get logged in user
        var user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Authenticated user not found"));

        // delete from db
        taskService.deleteTask(id, user);

        return ResponseEntity.ok("Task deleted");
    }

}
