package com.taskManagerSystem.TaskManagerSystem.services.impl;

import com.taskManagerSystem.TaskManagerSystem.dtos.TaskDTO;
import com.taskManagerSystem.TaskManagerSystem.entities.TaskEntity;
import com.taskManagerSystem.TaskManagerSystem.entities.UserEntity;
import com.taskManagerSystem.TaskManagerSystem.exceptions.AppException;
import com.taskManagerSystem.TaskManagerSystem.exceptions.ErrorCode;
import com.taskManagerSystem.TaskManagerSystem.repositories.TaskRepository;
import com.taskManagerSystem.TaskManagerSystem.requests.CreateTaskRequest;
import com.taskManagerSystem.TaskManagerSystem.requests.UpdateTaskRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.PaginationResult;
import com.taskManagerSystem.TaskManagerSystem.services.ITaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;

    private final UserServiceImpl userService;

    private final ModelMapper mapper;
    
    private TaskEntity findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TASK_NOT_EXISTED));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public TaskDTO createTask(CreateTaskRequest request) {
        List<UserEntity> team = new ArrayList<>();

        for (String email : request.getTeam()) {
            UserEntity user = userService.findUserByEmail(email);
            team.add(user);
        }

        TaskEntity task = TaskEntity.builder()
                .title(request.getTitle())
                .subTask(request.getSubTask())
                .team(team)
                .build();

        task = taskRepository.save(task);
        return mapper.map(task, TaskDTO.class);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public TaskDTO updateTask(UpdateTaskRequest request) {
        TaskEntity task = findTaskById(request.getTaskId());
        task.setTitle(request.getTitle());
        task.setSubTask(request.getSubTask());

        task = taskRepository.save(task);
        return mapper.map(task, TaskDTO.class);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public TaskDTO updateTeamTask(Long taskId, Set<String> team) {
        TaskEntity task = findTaskById(taskId);

        List<UserEntity> newTeam = new ArrayList<>();
        for (String email : team) {
            UserEntity user = userService.findUserByEmail(email);
            newTeam.add(user);
        }

        task.setTeam(newTeam);
        task = taskRepository.save(task);

        return mapper.map(task, TaskDTO.class);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public TaskDTO duplicateTask(Long taskId) {
        TaskEntity task = findTaskById(taskId);
        TaskEntity newTask = TaskEntity.builder()
                .title(task.getTitle())
                .subTask(task.getSubTask())
                .assets(task.getAssets())
                .team(task.getTeam())
                .activities(task.getActivities())
                .taskPriority(task.getTaskPriority())
                .stage(task.getStage())
                .build();

        newTask = taskRepository.save(newTask);
        return mapper.map(newTask, TaskDTO.class);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeTask(Long taskId) {
        TaskEntity task = findTaskById(taskId);
        task.setRemoved(true);
        taskRepository.save(task);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void restoreTask(Long taskId) {
        TaskEntity task = findTaskById(taskId);
        task.setRemoved(false);
        taskRepository.save(task);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteTask(Long taskId) {
        TaskEntity task = findTaskById(taskId);
        taskRepository.delete(task);
    }

    @Override
    public TaskDTO getTask(Long taskId) {
        return mapper.map(findTaskById(taskId), TaskDTO.class);
    }

    @Override
    public PaginationResult<TaskDTO> getTasksByStatus(boolean isRemoved, int pageNo, int pageSize,
                                              String sortBy, String sortDirection) {
        if (pageNo < 1) pageNo = 1;

        PaginationResult<TaskDTO> result = new PaginationResult<>();

        Sort.Direction direction = sortDirection.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        Page<TaskEntity> tasks = taskRepository.findAllTasks(isRemoved, pageable);

        List<TaskDTO> taskDTOs = tasks.getContent().stream()
                .map(task -> mapper.map(task, TaskDTO.class)).toList();

        result.setData(taskDTOs);
        result.setTotalItems(tasks.getTotalElements());
        result.setTotalPages(tasks.getTotalPages());

        return result;
    }
}
