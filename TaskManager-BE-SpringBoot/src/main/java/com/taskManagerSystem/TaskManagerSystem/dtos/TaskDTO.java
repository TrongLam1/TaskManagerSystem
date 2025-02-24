package com.taskManagerSystem.TaskManagerSystem.dtos;

import com.taskManagerSystem.TaskManagerSystem.enums.TaskPriority;
import com.taskManagerSystem.TaskManagerSystem.enums.TaskStage;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {

    private Long taskId;

    private LocalDateTime createdAt;

    private String title;

    private LocalDate deadline;

    private TaskPriority taskPriority;

    private TaskStage stage;

    private List<ActivityDTO> activities;

    private List<SubTaskDTO> subTask;

    private List<String> assets;

    private List<UserDTO> team;
}
