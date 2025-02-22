package com.taskManagerSystem.TaskManagerSystem.requests.notify;

import com.taskManagerSystem.TaskManagerSystem.enums.NotifyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {

    private Long taskId;

    private Set<String> team;

    private String content;

    private NotifyType type;
}
