package com.example.nerdy.service;

import com.example.nerdy.dto.TaskDto;
import com.example.nerdy.entity.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaskService {

    void addTask(TaskDto taskDto);

    TaskDto editTask(TaskDto taskDto);

    void deleteTask(TaskDto taskDto);

    List<Task> findAllByUserId(Integer id);

    void share(Integer id, String email);
}
