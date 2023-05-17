package org.example.dao;

import org.example.model.Task;

import java.util.List;

public interface TaskDAO {

    public boolean addTask(Task task);

    public List<Task> getAllTasks();

    public Task getTaskById(Long id);


    public boolean deleteTask(Long taskId);

    public boolean markTaskAsCompleted(Long taskId);
}
