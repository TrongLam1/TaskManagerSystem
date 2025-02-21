package com.taskManagerSystem.TaskManagerSystem.requests.subTask;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubTaskRequest {

    private Long subTaskId;

    private String content;

    private String title;

    private Long taskId;
}
