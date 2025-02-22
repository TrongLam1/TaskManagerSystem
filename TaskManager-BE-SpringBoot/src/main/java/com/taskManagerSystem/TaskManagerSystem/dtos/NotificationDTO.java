package com.taskManagerSystem.TaskManagerSystem.dtos;

import com.taskManagerSystem.TaskManagerSystem.enums.NotifyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private Long notificationId;

    private LocalDateTime createdAt;

    private String content;

    private NotifyType type;

    private TaskDTO task;
}
