package com.taskManagerSystem.TaskManagerSystem.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtAuthenticationResponse {

    private String token;

    private String refreshToken;

    private String name;
}
