package com.taskManagerSystem.TaskManagerSystem.services.impl;

import com.taskManagerSystem.TaskManagerSystem.entities.PermissionEntity;
import com.taskManagerSystem.TaskManagerSystem.repositories.PermissionRepository;
import com.taskManagerSystem.TaskManagerSystem.requests.PermissionRequest;
import com.taskManagerSystem.TaskManagerSystem.services.IPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionServiceImpl implements IPermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public void create(PermissionRequest request) {
        PermissionEntity permission = PermissionEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        permission = permissionRepository.save(permission);
    }
}
