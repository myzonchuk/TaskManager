package com.example.nerdy.repository;

import com.example.nerdy.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task findTaskByTaskName(String taskName);

    void deleteByTaskName(String taskName);

    List<Task>findByUser_Id(Integer id);
}
