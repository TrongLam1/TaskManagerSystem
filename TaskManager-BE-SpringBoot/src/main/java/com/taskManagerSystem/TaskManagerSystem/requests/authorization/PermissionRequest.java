package com.taskManagerSystem.TaskManagerSystem.requests.authorization;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionRequest {

    private String name;

    private String description;
}
