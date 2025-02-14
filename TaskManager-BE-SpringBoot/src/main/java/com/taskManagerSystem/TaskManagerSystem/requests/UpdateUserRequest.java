package com.taskManagerSystem.TaskManagerSystem.requests;

import com.taskManagerSystem.TaskManagerSystem.entities.RoleEntity;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {

    private String name;

    private String title;

    private String email;

    private Set<RoleEntity> roles;
}
