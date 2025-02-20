package com.taskManagerSystem.TaskManagerSystem.services;

import com.taskManagerSystem.TaskManagerSystem.dtos.TaskDTO;
import com.taskManagerSystem.TaskManagerSystem.requests.task.CreateTaskRequest;
import com.taskManagerSystem.TaskManagerSystem.requests.task.UpdateTaskRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.PaginationResult;

import java.util.Set;

public interface ITaskService {

    TaskDTO createTask(CreateTaskRequest request);

    TaskDTO updateTask(UpdateTaskRequest request);

    TaskDTO updateTeamTask(Long taskId, Set<String> team);

    TaskDTO duplicateTask(Long taskId);

    void removeTask(Long taskId);

    void restoreTask(Long taskId);

    void deleteTask(Long taskId);

    TaskDTO getTask(Long taskId);

    PaginationResult<TaskDTO> getTasksByStatus(boolean isRemoved, int pageNo, int pageSize, String sortBy, String sortDirection);
}

