package com.taskManagerSystem.TaskManagerSystem.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogInRequest {

    @Email
    @NotBlank(message = "Email is mandatory.")
    private String email;

    @NotBlank(message = "Password is mandatory.")
    private String password;
}
