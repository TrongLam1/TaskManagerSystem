package com.taskManagerSystem.TaskManagerSystem.requests.task;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTaskRequest {

    private String title;

    private LocalDate deadline;

    private List<String> assets;

    private Set<String> team;
}
