package com.taskManagerSystem.TaskManagerSystem.controllers;

import com.taskManagerSystem.TaskManagerSystem.dtos.UserDTO;
import com.taskManagerSystem.TaskManagerSystem.requests.user.CreateUserRequest;
import com.taskManagerSystem.TaskManagerSystem.requests.user.UpdateUserRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.PaginationResult;
import com.taskManagerSystem.TaskManagerSystem.responses.ResponseData;
import com.taskManagerSystem.TaskManagerSystem.services.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Controller")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseData<UserDTO> createUser(@RequestBody CreateUserRequest request) {
        try {
            log.info("Request: Create new user");
            return new ResponseData<>(HttpStatus.CREATED.value(), "Create new user",
                    userService.createUser(request));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseData<UserDTO> updateUser(@RequestBody UpdateUserRequest request) {
        try {
            log.info("Request: Update user");
            return new ResponseData<>(HttpStatus.CREATED.value(), "Update user",
                    userService.updateUser(request));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/profile")
    public ResponseData<UserDTO> getProfile(Principal principal) {
        try {
            log.info("Request: Get profile");
            return new ResponseData<>(HttpStatus.CREATED.value(), "Get profile",
                    userService.getProfile());
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseData<PaginationResult<UserDTO>> getAllUsers(
            @RequestParam("isActive") boolean isActive,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("sortDirection") String sortDirection
    ) {
        try {
            log.info("Request: Get all users");
            return new ResponseData<>(HttpStatus.OK.value(), "Get all users",
                    userService.getAllUsers(isActive, pageNo, pageSize, sortBy, sortDirection));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseData<String> removeUsers(@RequestBody Set<String> emails) {
        try {
            log.info("Request: Remove users");
            userService.removeUsers(emails);
            return new ResponseData<>(HttpStatus.OK.value(), "Remove users",
                    "Remove users successfully.");
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
