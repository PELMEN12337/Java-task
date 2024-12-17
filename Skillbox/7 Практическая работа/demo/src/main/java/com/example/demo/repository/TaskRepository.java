package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TaskRepository extends ReactiveCrudRepository<Task, String> {
}
