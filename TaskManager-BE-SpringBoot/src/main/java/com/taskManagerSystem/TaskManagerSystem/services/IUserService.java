package com.taskManagerSystem.TaskManagerSystem.services;

import com.taskManagerSystem.TaskManagerSystem.entities.UserEntity;
import com.taskManagerSystem.TaskManagerSystem.requests.CreateUserRequest;

public interface IUserService {

    UserEntity createUser(CreateUserRequest request);

    UserEntity getUserByEmail(String email);
}
