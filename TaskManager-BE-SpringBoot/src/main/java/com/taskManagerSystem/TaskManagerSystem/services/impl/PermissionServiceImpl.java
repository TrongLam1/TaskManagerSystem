package com.taskManagerSystem.TaskManagerSystem.services.impl;

import com.taskManagerSystem.TaskManagerSystem.dtos.PermissionDTO;
import com.taskManagerSystem.TaskManagerSystem.entities.PermissionEntity;
import com.taskManagerSystem.TaskManagerSystem.exceptions.AppException;
import com.taskManagerSystem.TaskManagerSystem.exceptions.ErrorCode;
import com.taskManagerSystem.TaskManagerSystem.repositories.PermissionRepository;
import com.taskManagerSystem.TaskManagerSystem.requests.authorization.PermissionRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.PaginationResult;
import com.taskManagerSystem.TaskManagerSystem.services.IPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionServiceImpl implements IPermissionService {

    private final PermissionRepository permissionRepository;

    private final ModelMapper mapper;

    public PermissionEntity findPermissionByName(String permissionName) {
        return permissionRepository.findByName(permissionName.toUpperCase(Locale.ROOT))
                .orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_EXISTED));
    }

    @Override
    public PermissionDTO createPermission(PermissionRequest request) {
        if (permissionRepository.findByName(request.getName().toUpperCase(Locale.ROOT)).isPresent()) {
            throw new AppException(ErrorCode.PERMISSION_EXISTED);
        }

        PermissionEntity permission = PermissionEntity.builder()
                .name(request.getName().toUpperCase(Locale.ROOT))
                .description(request.getDescription())
                .build();

        permission = permissionRepository.save(permission);

        return mapper.map(permission, PermissionDTO.class);
    }

    @Override
    public PermissionDTO updatePermission(PermissionRequest request) {
        PermissionEntity permission = findPermissionByName(request.getName());
        permission.setDescription(request.getDescription());
        permission = permissionRepository.save(permission);
        return mapper.map(permission, PermissionDTO.class);
    }

    @Override
    public void deletePermission(String permissionName) {
        PermissionEntity permission = findPermissionByName(permissionName);
        permissionRepository.delete(permission);
    }

    @Override
    public PaginationResult<PermissionDTO> getPermissions() {
        List<PermissionEntity> permissions = permissionRepository.findAll();

        PaginationResult<PermissionDTO> result = new PaginationResult<>();
        List<PermissionDTO> permissionDTOs = permissions.stream()
                .map(permission -> mapper.map(permission, PermissionDTO.class))
                .toList();

        result.setData(permissionDTOs);
        result.setTotalItems((long) permissions.size());
        result.setTotalPages(1);

        return result;
    }
}
