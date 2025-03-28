package com.taskManagerSystem.TaskManagerSystem.requests.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "Name is mandatory.")
    private String name;

    @Email
    @NotBlank(message = "Email is mandatory.")
    private String email;

    private String title;

    @NotBlank(message = "Password is mandatory.")
    private String password;

    private String[] roles;
}
