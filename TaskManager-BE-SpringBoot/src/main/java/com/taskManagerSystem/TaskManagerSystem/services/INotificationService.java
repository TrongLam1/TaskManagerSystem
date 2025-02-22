package com.taskManagerSystem.TaskManagerSystem.services;

import com.taskManagerSystem.TaskManagerSystem.dtos.NotificationDTO;
import com.taskManagerSystem.TaskManagerSystem.requests.notify.NotificationRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.PaginationResult;

import java.util.List;

public interface INotificationService {

    NotificationDTO createNotification(NotificationRequest request);

    PaginationResult<NotificationDTO> getNotifications(int pageNo, int pageSize);

    List<NotificationDTO> getLimitNewNotifications(int limit);
}
