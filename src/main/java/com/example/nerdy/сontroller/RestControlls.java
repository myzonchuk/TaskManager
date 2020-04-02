package com.example.nerdy.сontroller;

import com.example.nerdy.dto.TaskDto;
import com.example.nerdy.dto.UserRegistrationDto;
import com.example.nerdy.entity.Task;
import com.example.nerdy.service.TaskService;
import com.example.nerdy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestControlls {

    private UserService userService;
    private TaskService taskService;

    @Autowired
    public RestControlls(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @PostMapping("/registration")
    public HttpStatus registration(@RequestBody UserRegistrationDto dto) {
        userService.registration(dto);
        return HttpStatus.OK;
    }

    @PostMapping("/add_task")
    public HttpStatus addTask(@RequestBody TaskDto dto) {
        taskService.addTask(dto);
        return HttpStatus.OK;
    }

    @PostMapping("/edit_task")
    public TaskDto updateTask(@RequestBody TaskDto dto) {
        return taskService.editTask(dto);
    }

    @GetMapping("/delete_task")
    public HttpStatus deleteTaskById(@RequestBody TaskDto dto) {
        taskService.deleteTask(dto);
        return HttpStatus.OK;
    }

    @GetMapping("/get_all_task/{id}")
    public List<Task> getAll(@PathVariable("id") Integer userId) {
        return taskService.findAllByUserId(userId);
    }
}
