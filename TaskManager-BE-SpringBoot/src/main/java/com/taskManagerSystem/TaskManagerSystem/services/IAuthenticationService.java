package com.taskManagerSystem.TaskManagerSystem.services;

import com.taskManagerSystem.TaskManagerSystem.requests.LogInRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.JwtAuthenticationResponse;

public interface IAuthenticationService {

    JwtAuthenticationResponse logIn(LogInRequest request);
}
