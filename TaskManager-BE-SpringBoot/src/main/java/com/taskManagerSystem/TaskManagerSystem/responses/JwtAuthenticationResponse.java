package com.taskManagerSystem.TaskManagerSystem.responses;

import com.taskManagerSystem.TaskManagerSystem.dtos.UserDTO;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtAuthenticationResponse {

    private UserDTO user;

    private String token;

    private String refreshToken;
}
