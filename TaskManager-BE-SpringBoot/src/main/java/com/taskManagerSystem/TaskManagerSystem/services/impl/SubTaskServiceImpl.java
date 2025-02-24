package com.taskManagerSystem.TaskManagerSystem.services.impl;

import com.taskManagerSystem.TaskManagerSystem.dtos.SubTaskDTO;
import com.taskManagerSystem.TaskManagerSystem.entities.SubTaskEntity;
import com.taskManagerSystem.TaskManagerSystem.entities.TaskEntity;
import com.taskManagerSystem.TaskManagerSystem.exceptions.AppException;
import com.taskManagerSystem.TaskManagerSystem.exceptions.ErrorCode;
import com.taskManagerSystem.TaskManagerSystem.repositories.SubTaskRepository;
import com.taskManagerSystem.TaskManagerSystem.requests.subTask.SubTaskRequest;
import com.taskManagerSystem.TaskManagerSystem.services.ISubTaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubTaskServiceImpl implements ISubTaskService {

    private final SubTaskRepository subTaskRepository;

    private final TaskServiceImpl taskService;

    private final ModelMapper mapper;

    private SubTaskEntity findSubTaskById(Long subTaskId) {
        return subTaskRepository.findById(subTaskId)
                .orElseThrow(() -> new AppException(ErrorCode.SUB_TASK_NOT_EXISTED));
    }

    @Override
    public SubTaskDTO createSubTask(SubTaskRequest request) {
        TaskEntity task = taskService.findTaskById(request.getTaskId());
        SubTaskEntity subTask = SubTaskEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .task(task)
                .build();

        subTask = subTaskRepository.save(subTask);
        return mapper.map(subTask, SubTaskDTO.class);
    }

    @Override
    public SubTaskDTO updateSubTask(SubTaskRequest request) {
        SubTaskEntity subTask = findSubTaskById(request.getSubTaskId());
        subTask.setTitle(request.getTitle());
        subTask.setContent(request.getContent());
        subTask = subTaskRepository.save(subTask);
        return mapper.map(subTask, SubTaskDTO.class);
    }

    @Override
    public void deleteSubTask(Long subTaskId) {
        SubTaskEntity subTask = findSubTaskById(subTaskId);
        subTaskRepository.delete(subTask);
    }
}
