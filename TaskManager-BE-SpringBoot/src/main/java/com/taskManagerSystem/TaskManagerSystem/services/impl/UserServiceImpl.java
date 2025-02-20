package com.taskManagerSystem.TaskManagerSystem.services.impl;

import com.taskManagerSystem.TaskManagerSystem.dtos.UserDTO;
import com.taskManagerSystem.TaskManagerSystem.entities.UserEntity;
import com.taskManagerSystem.TaskManagerSystem.exceptions.AppException;
import com.taskManagerSystem.TaskManagerSystem.exceptions.ErrorCode;
import com.taskManagerSystem.TaskManagerSystem.repositories.UserRepository;
import com.taskManagerSystem.TaskManagerSystem.requests.user.CreateUserRequest;
import com.taskManagerSystem.TaskManagerSystem.requests.user.UpdateUserRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.PaginationResult;
import com.taskManagerSystem.TaskManagerSystem.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper mapper;

    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    @Override
    public UserDTO createUser(CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        String password = passwordEncoder.encode(request.getPassword());

        UserEntity newUser = UserEntity.builder()
                .name(request.getName())
                .password(password)
                .email(request.getEmail())
                .isActive(true)
                .build();

        userRepository.save(newUser);

        return mapper.map(newUser, UserDTO.class);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserEntity user = findUserByEmail(email);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    @PostAuthorize("returnObject.email == authentication.name")
    public UserDTO getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        return getUserByEmail(authentication.getName());
    }

    @Override
    public UserDTO updateUser(UpdateUserRequest request) {
        UserEntity user = findUserByEmail(request.getEmail());
        user.setName(request.getName());
        user.setTitle(request.getTitle());

        user = userRepository.save(user);

        return mapper.map(user, UserDTO.class);
    }

    @Override
    public PaginationResult<UserDTO> getAllUsers(boolean isActive, int pageNo, int pageSize,
                                                 String sortBy, String sortDirection) {
        if (pageNo < 1) pageNo = 1;

        PaginationResult<UserDTO> result = new PaginationResult<>();

        Sort.Direction direction = sortDirection.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        Page<UserEntity> users = userRepository.findAllUser(isActive, pageable);

        List<UserDTO> usersDTO = users.getContent().stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .toList();

        result.setData(usersDTO);
        result.setTotalItems(users.getTotalElements());
        result.setTotalPages(users.getTotalPages());

        return result;
    }

    @Override
    public void removeUsers(Set<String> emails) {
        for (String email : emails) {
            UserEntity user = findUserByEmail(email);
            user.setActive(false);
            userRepository.save(user);
        }
    }
}
