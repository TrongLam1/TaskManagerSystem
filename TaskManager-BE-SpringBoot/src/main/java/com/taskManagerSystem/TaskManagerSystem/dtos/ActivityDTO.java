package com.taskManagerSystem.TaskManagerSystem.dtos;

import com.taskManagerSystem.TaskManagerSystem.enums.TaskActivity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityDTO {

    private Long activityId;

    private TaskActivity activity;

    private LocalDateTime createdAt;

    private String content;

    private UserDTO by;
}
