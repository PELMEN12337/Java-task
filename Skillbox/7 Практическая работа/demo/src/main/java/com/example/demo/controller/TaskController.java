package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public Flux<Task> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Task> findById(@PathVariable String id) {
        return taskService.findById(id);
    }

    @PostMapping
    public Mono<Task> create(@RequestBody Task task) {
        return taskService.save(task);
    }

    @PutMapping
    public Mono<Task> update(@RequestBody Task task) {
        return taskService.update(task);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable String id) {
        return taskService.deleteById(id);
    }

    @PostMapping("/{taskId}/addObserver/{observerId}")
    public Mono<Task> addObserver(@PathVariable String taskId, @PathVariable String observerId) {
        return taskService.addObserver(taskId, observerId);
    }
}
