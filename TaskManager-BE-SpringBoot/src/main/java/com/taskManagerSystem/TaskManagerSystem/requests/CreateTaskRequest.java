package com.taskManagerSystem.TaskManagerSystem.requests;

import com.taskManagerSystem.TaskManagerSystem.dtos.ActivityDTO;
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

    private List<ActivityDTO> activities;

    private String subTask;

    private List<String> assets;

    private Set<String> team;
}
