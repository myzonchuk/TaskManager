package com.example.nerdy.mapper;

import com.example.nerdy.dto.TaskDto;
import com.example.nerdy.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task convertToEntity(TaskDto dto) {
        return Task.builder()
                .taskName(dto.getTaskName())
                .taskContent(dto.getContent())
                .build();
    }

    public TaskDto convertToDto(Task task) {
        return TaskDto.builder()
                .taskName(task.getTaskName())
                .content(task.getTaskContent())
                .build();
    }
}
