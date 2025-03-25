package com.taskManagerSystem.TaskManagerSystem.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long userId;

    private LocalDateTime createdAt;

    private String name;

    private String title;

    private String email;

    private Set<RoleDTO> roles;

    private boolean isActive;
}
