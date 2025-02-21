package com.taskManagerSystem.TaskManagerSystem.requests.task;

import com.taskManagerSystem.TaskManagerSystem.enums.TaskPriority;
import com.taskManagerSystem.TaskManagerSystem.enums.TaskStage;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTaskRequest {

    private Long taskId;

    private LocalDateTime deadline;

    private TaskPriority taskPriority;

    private TaskStage stage;

    private List<String> assets;
}
