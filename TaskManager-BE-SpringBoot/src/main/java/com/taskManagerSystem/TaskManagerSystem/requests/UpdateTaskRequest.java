package com.taskManagerSystem.TaskManagerSystem.requests;

import com.taskManagerSystem.TaskManagerSystem.dtos.ActivityDTO;
import com.taskManagerSystem.TaskManagerSystem.enums.TaskPriority;
import com.taskManagerSystem.TaskManagerSystem.enums.TaskStage;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTaskRequest {

    private Long taskId;

    private String title;

    private TaskPriority taskPriority;

    private TaskStage stage;

    private List<ActivityDTO> activities;

    private String subTask;

    private List<String> assets;
}
