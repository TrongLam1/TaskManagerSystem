package com.taskManagerSystem.TaskManagerSystem.services;

import com.taskManagerSystem.TaskManagerSystem.dtos.SubTaskDTO;
import com.taskManagerSystem.TaskManagerSystem.requests.subTask.SubTaskRequest;

public interface ISubTaskService {

    SubTaskDTO createSubTask(SubTaskRequest request);

    SubTaskDTO updateSubTask(SubTaskRequest request);

    void deleteSubTask(Long subTaskId);
}
