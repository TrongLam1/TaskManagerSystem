package com.taskManagerSystem.TaskManagerSystem.dtos;

import com.taskManagerSystem.TaskManagerSystem.entities.RoleEntity;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long userId;

    private String name;

    private String title;

    private Set<RoleEntity> roles;

    private String email;
}
