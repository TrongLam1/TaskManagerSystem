package com.taskManagerSystem.TaskManagerSystem.requests.task;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTaskRequest {

    private String title;

    private String subTask;

    private List<String> assets;

    private Set<String> team;
}
