package com.taskManagerSystem.TaskManagerSystem.services.impl;

import com.taskManagerSystem.TaskManagerSystem.dtos.ActivityDTO;
import com.taskManagerSystem.TaskManagerSystem.entities.ActivityEntity;
import com.taskManagerSystem.TaskManagerSystem.entities.TaskEntity;
import com.taskManagerSystem.TaskManagerSystem.entities.UserEntity;
import com.taskManagerSystem.TaskManagerSystem.enums.TaskActivity;
import com.taskManagerSystem.TaskManagerSystem.exceptions.AppException;
import com.taskManagerSystem.TaskManagerSystem.exceptions.ErrorCode;
import com.taskManagerSystem.TaskManagerSystem.repositories.ActivityRepository;
import com.taskManagerSystem.TaskManagerSystem.repositories.TaskRepository;
import com.taskManagerSystem.TaskManagerSystem.requests.activity.ActivityRequest;
import com.taskManagerSystem.TaskManagerSystem.services.IActivityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements IActivityService {

    private final ActivityRepository activityRepository;

    private final TaskRepository taskRepository;

    private final UserServiceImpl userService;

    private final ModelMapper mapper;

    @Override
    public ActivityDTO createActivity(ActivityRequest request) {
        TaskEntity task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new AppException(ErrorCode.TASK_NOT_EXISTED));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        UserEntity user = userService.findUserByEmail(authentication.getName());

        ActivityEntity newActivity = ActivityEntity.builder()
                .createdAt(LocalDateTime.now())
                .content(request.getContent())
                .by(user)
                .task(task)
                .build();

        switch (request.getActivity()) {
            case "Started":
                newActivity.setActivity(TaskActivity.STARTED);
                break;
            case "In Progress":
                newActivity.setActivity(TaskActivity.IN_PROGRESS);
                break;
            case "Bug":
                newActivity.setActivity(TaskActivity.BUG);
                break;
            case "Completed":
                newActivity.setActivity(TaskActivity.COMPLETED);
                break;
            case "Commented":
                newActivity.setActivity(TaskActivity.COMMENTED);
                break;
            default:
                newActivity.setActivity(TaskActivity.ASSIGNED);
                break;
        }

        newActivity = activityRepository.save(newActivity);

        task.getActivities().add(newActivity);
        taskRepository.save(task);

        return mapper.map(newActivity, ActivityDTO.class);
    }

    @Override
    public List<ActivityDTO> getActivities(Long taskId) {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new AppException(ErrorCode.TASK_NOT_EXISTED));

        List<ActivityEntity> listActivities = activityRepository.findAllByTask(task);

        return listActivities.stream()
                .map(activity -> mapper.map(activity, ActivityDTO.class))
                .toList();
    }
}
