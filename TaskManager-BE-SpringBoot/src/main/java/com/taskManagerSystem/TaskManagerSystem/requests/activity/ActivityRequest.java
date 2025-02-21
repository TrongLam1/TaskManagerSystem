package com.taskManagerSystem.TaskManagerSystem.requests.activity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequest {

    private Long taskId;

    private String activity;

    private String content;
}
