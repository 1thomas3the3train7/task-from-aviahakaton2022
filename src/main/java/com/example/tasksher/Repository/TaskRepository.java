package com.example.tasksher.Repository;

import com.example.tasksher.Model.Task;

public interface TaskRepository {
    void save(Task task);
    void delete(Task task);
    Task getTaskByToken(final String token);
}
