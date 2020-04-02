package com.example.nerdy.service.impl;

import com.example.nerdy.constant.ExceptionMessage;
import com.example.nerdy.dto.TaskDto;
import com.example.nerdy.entity.Task;
import com.example.nerdy.entity.User;
import com.example.nerdy.exceptions.InvalidEmailException;
import com.example.nerdy.mapper.TaskMapper;
import com.example.nerdy.repository.TaskRepository;
import com.example.nerdy.repository.UserRepository;
import com.example.nerdy.service.EmailService;
import com.example.nerdy.service.TaskService;
import com.example.nerdy.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    private TaskMapper taskMapper;
    private TaskRepository taskRepository;
    private Validator validator;
    private UserRepository userRepository;
    private EmailService emailService;

    @Autowired
    public TaskServiceImpl(TaskMapper taskMapper,
                           TaskRepository taskRepository) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTask(TaskDto taskDto) {
        taskRepository.save(taskMapper.convertToEntity(taskDto));
    }

    @Override
    public TaskDto editTask(TaskDto taskDto) {
        Task task = taskRepository.findTaskByTaskName(taskDto.getTaskName());
        task.setTaskContent(taskDto.getContent());
        return taskMapper.convertToDto(taskRepository.save(task));
    }

    @Override
    public void deleteTask(TaskDto taskDto) {
        taskRepository.deleteByTaskName(taskDto.getTaskName());
    }

    @Override
    public List<Task> findAllByUserId(Integer userId) {
        return taskRepository.findByUser_Id(userId);
    }

    @Override
    public void share(Integer id, String email) {
        if (!validator.validateEmail(email)) {
            throw new InvalidEmailException(ExceptionMessage.INVALID_EMAIL);
        }
        Task task = taskRepository.getOne(id);
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException(
                    String.format("User with email %s not found", email));
        }
        taskRepository.save(task);
        emailService.sendEmail(user.getEmail(), "You have a new common task in Task Manager");
    }
}
