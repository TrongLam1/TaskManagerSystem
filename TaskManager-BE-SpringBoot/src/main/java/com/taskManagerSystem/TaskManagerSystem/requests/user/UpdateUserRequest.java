package com.taskManagerSystem.TaskManagerSystem.requests.user;

import com.taskManagerSystem.TaskManagerSystem.entities.RoleEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @Email
    @NotBlank(message = "Email is mandatory.")
    private String email;

    private Set<RoleEntity> roles;
}
