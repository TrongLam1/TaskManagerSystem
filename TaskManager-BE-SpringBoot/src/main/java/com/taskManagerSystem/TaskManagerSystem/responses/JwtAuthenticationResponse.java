package com.taskManagerSystem.TaskManagerSystem.responses;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtAuthenticationResponse {

    private String token;

    private String refreshToken;

    private String name;
}
