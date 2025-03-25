package com.taskManagerSystem.TaskManagerSystem.services;

import com.taskManagerSystem.TaskManagerSystem.dtos.UserDTO;
import com.taskManagerSystem.TaskManagerSystem.requests.user.ChangePasswordRequest;
import com.taskManagerSystem.TaskManagerSystem.requests.user.CreateUserRequest;
import com.taskManagerSystem.TaskManagerSystem.requests.user.UpdateUserRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.PaginationResult;

import java.util.Set;

public interface IUserService {

    UserDTO createUser(CreateUserRequest request);

    UserDTO getUserByEmail(String email);

    UserDTO getProfile();

    UserDTO updateUser(UpdateUserRequest request);

    void resetPassword(String email);

    void changePassword(ChangePasswordRequest request);

    PaginationResult<UserDTO> getAllUsers(boolean isActive, int pageNo, int pageSize, String sortBy, String sortDirection);

    void restoreUsers(Set<String> emails);

    void removeUsers(Set<String> emails);
}
