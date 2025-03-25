package com.taskManagerSystem.TaskManagerSystem.requests.user;

import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {

    @Length(min = 8, message = "Password must be more than 7 characters.")
    private String oldPassword;

    @Length(min = 8, message = "Password must be more than 7 characters.")
    private String newPassword;
}
