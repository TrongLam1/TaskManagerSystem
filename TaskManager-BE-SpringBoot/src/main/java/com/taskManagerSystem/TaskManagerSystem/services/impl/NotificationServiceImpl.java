package com.taskManagerSystem.TaskManagerSystem.services.impl;

import com.taskManagerSystem.TaskManagerSystem.dtos.NotificationDTO;
import com.taskManagerSystem.TaskManagerSystem.entities.NotificationEntity;
import com.taskManagerSystem.TaskManagerSystem.entities.TaskEntity;
import com.taskManagerSystem.TaskManagerSystem.entities.UserEntity;
import com.taskManagerSystem.TaskManagerSystem.exceptions.AppException;
import com.taskManagerSystem.TaskManagerSystem.exceptions.ErrorCode;
import com.taskManagerSystem.TaskManagerSystem.repositories.NotificationRepository;
import com.taskManagerSystem.TaskManagerSystem.requests.notify.NotificationRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.PaginationResult;
import com.taskManagerSystem.TaskManagerSystem.services.INotificationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements INotificationService {

    private final NotificationRepository notificationRepository;

    private final UserServiceImpl userService;

    private final TaskServiceImpl taskService;

    private final ModelMapper mapper;

    @Override
    public NotificationDTO createNotification(NotificationRequest request) {
        List<UserEntity> team = new ArrayList<>();
        for (String email : request.getTeam()) {
            UserEntity member = userService.findUserByEmail(email);
            team.add(member);
        }

        TaskEntity task = taskService.findTaskById(request.getTaskId());

        NotificationEntity notify = NotificationEntity.builder()
                .content(request.getContent())
                .type(request.getType())
                .task(task)
                .team(team)
                .build();

        notify = notificationRepository.save(notify);
        return mapper.map(notify, NotificationDTO.class);
    }

    @Override
    public PaginationResult<NotificationDTO> getNotifications(int pageNo, int pageSize) {
        if (pageNo < 1) pageNo = 1;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        UserEntity user = userService.findUserByEmail(authentication.getName());

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("createdAt").descending());
        Page<NotificationEntity> notifies = notificationRepository.findByUser(user.getUserId(), pageable);

        List<NotificationDTO> notifiesDTOs = notifies.getContent().stream()
                .map(notify -> mapper.map(notify, NotificationDTO.class)).toList();

        PaginationResult<NotificationDTO> result = new PaginationResult<>();
        result.setData(notifiesDTOs);
        result.setTotalItems(notifies.getTotalElements());
        result.setTotalPages(notifies.getTotalPages());
        return result;
    }

    @Override
    public List<NotificationDTO> getLimitNewNotifications(int limit) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        UserEntity user = userService.findUserByEmail(authentication.getName());
        Pageable pageable = PageRequest.of(0, 5, Sort.by("createdAt").descending());
        Page<NotificationEntity> notifies = notificationRepository.findByUser(user.getUserId(), pageable);
        return notifies.getContent().stream()
                .map(notify -> mapper.map(notify, NotificationDTO.class))
                .toList();
    }
}
