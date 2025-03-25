package com.taskManagerSystem.TaskManagerSystem.controllers;

import com.taskManagerSystem.TaskManagerSystem.requests.LogInRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.JwtAuthenticationResponse;
import com.taskManagerSystem.TaskManagerSystem.responses.ResponseData;
import com.taskManagerSystem.TaskManagerSystem.services.impl.AuthenticationServiceImpl;
import com.taskManagerSystem.TaskManagerSystem.services.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Authentication Controller")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    private final UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseData<JwtAuthenticationResponse> logIn(@RequestBody LogInRequest request) {
        try {
            log.info("Login: {}", request.getEmail());
            return new ResponseData<>(HttpStatus.OK.value(), "Login success.",
                    authenticationService.logIn(request));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/reset-password")
    public ResponseData<String> resetPassword(@RequestParam String email) {
        try {
            log.info("Request: Reset password user {}", email);
            userService.resetPassword(email);
            return new ResponseData<>(HttpStatus.OK.value(), "Reset password.");
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
