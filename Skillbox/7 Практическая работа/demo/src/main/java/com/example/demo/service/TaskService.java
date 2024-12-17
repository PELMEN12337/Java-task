package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant; // Импорт для Instant
import java.util.Set; // Импорт для Set

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Flux<Task> findAll() {
        return taskRepository.findAll().flatMap(this::populateTaskWithUsers);
    }

    public Mono<Task> findById(String id) {
        return taskRepository.findById(id).flatMap(this::populateTaskWithUsers);
    }

    public Mono<Task> save(Task task) {
        task.setCreatedAt(Instant.now()); // Использование Instant
        return taskRepository.save(task);
    }

    public Mono<Task> update(Task task) {
        task.setUpdatedAt(Instant.now()); // Использование Instant
        return taskRepository.save(task);
    }

    public Mono<Void> deleteById(String id) {
        return taskRepository.deleteById(id);
    }

    public Mono<Task> addObserver(String taskId, String observerId) {
        return taskRepository.findById(taskId)
                .flatMap(task -> {
                    task.getObserverIds().add(observerId); // Использование Set
                    return taskRepository.save(task);
                });
    }

    private Mono<Task> populateTaskWithUsers(Task task) {
        Mono<User> author = userRepository.findById(task.getAuthorId());
        Mono<User> assignee = userRepository.findById(task.getAssigneeId());
        Flux<User> observers = Flux.fromIterable(task.getObserverIds())
                .flatMap(userRepository::findById);

        return Mono.zip(author, assignee, observers.collectList())
                .map(tuple -> {
                    task.setAuthor(tuple.getT1());
                    task.setAssignee(tuple.getT2());
                    task.setObservers(Set.copyOf(tuple.getT3())); // Использование Set
                    return task;
                });
    }
}
