package com.example.demo.mapper;

import com.example.demo.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toTask(Task task);
}
