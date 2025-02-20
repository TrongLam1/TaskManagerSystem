package com.taskManagerSystem.TaskManagerSystem.requests.authorization;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequest {

    private String name;

    private Set<String> permissions;
}
