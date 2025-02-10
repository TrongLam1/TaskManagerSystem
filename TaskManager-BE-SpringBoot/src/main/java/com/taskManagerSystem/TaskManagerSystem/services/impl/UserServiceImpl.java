package com.taskManagerSystem.TaskManagerSystem.services.impl;

import com.taskManagerSystem.TaskManagerSystem.entities.UserEntity;
import com.taskManagerSystem.TaskManagerSystem.exceptions.AlreadyExistsException;
import com.taskManagerSystem.TaskManagerSystem.exceptions.NotFoundException;
import com.taskManagerSystem.TaskManagerSystem.repositories.UserRepository;
import com.taskManagerSystem.TaskManagerSystem.requests.CreateUserRequest;
import com.taskManagerSystem.TaskManagerSystem.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email: " + email));
    }

    @Override
    public UserEntity createUser(CreateUserRequest request) {
        try {
            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new AlreadyExistsException(request.getEmail());
            }

            String password = passwordEncoder.encode(request.getPassword());

            UserEntity newUser = UserEntity.builder()
                    .name(request.getName())
                    .password(password)
                    .email(request.getEmail())
                    .isActive(true)
                    .build();

            userRepository.save(newUser);

            return newUser;
        } catch (AlreadyExistsException e) {
            log.error("Email already exists: {}", request.getEmail(), e);
            throw e;
        } catch (Exception e) {
            log.error("An error occurred while creating the user", e);
            throw new RuntimeException("Unable to create user, please try again later.");
        }
    }

}
