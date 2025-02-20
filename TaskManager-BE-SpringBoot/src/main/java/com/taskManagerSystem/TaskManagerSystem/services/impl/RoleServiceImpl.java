package com.taskManagerSystem.TaskManagerSystem.services.impl;

import com.taskManagerSystem.TaskManagerSystem.dtos.RoleDTO;
import com.taskManagerSystem.TaskManagerSystem.entities.PermissionEntity;
import com.taskManagerSystem.TaskManagerSystem.entities.RoleEntity;
import com.taskManagerSystem.TaskManagerSystem.exceptions.AppException;
import com.taskManagerSystem.TaskManagerSystem.exceptions.ErrorCode;
import com.taskManagerSystem.TaskManagerSystem.repositories.RoleRepository;
import com.taskManagerSystem.TaskManagerSystem.requests.authorization.RoleRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.PaginationResult;
import com.taskManagerSystem.TaskManagerSystem.services.IRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;

    private final PermissionServiceImpl permissionService;

    private final ModelMapper mapper;

    private RoleEntity findRoleByName(String roleName) {
        return roleRepository.findByName(roleName.toUpperCase(Locale.ROOT))
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_EXISTED));
    }

    @Override
    public RoleDTO createRole(RoleRequest request) {
        if (roleRepository.findByName(request.getName()).isPresent()) {
            throw new AppException(ErrorCode.ROLE_EXISTED);
        }

        Set<PermissionEntity> permissions = new HashSet<>();
        for (String permissionName : request.getPermissions()) {
            PermissionEntity permission = permissionService.findPermissionByName(permissionName);
            permissions.add(permission);
        }

        RoleEntity role = RoleEntity.builder()
                .name(request.getName().toUpperCase(Locale.ROOT))
                .permissions(permissions)
                .build();

        role = roleRepository.save(role);

        return mapper.map(role, RoleDTO.class);
    }

    @Override
    public RoleDTO updateRole(RoleRequest request) {
        RoleEntity role = findRoleByName(request.getName());

        Set<PermissionEntity> permissions = new HashSet<>();
        for (String permissionName : request.getPermissions()) {
            PermissionEntity permission = permissionService.findPermissionByName(permissionName.toUpperCase(Locale.ROOT));
            permissions.add(permission);
        }

        role.setPermissions(permissions);
        role = roleRepository.save(role);
        return mapper.map(role, RoleDTO.class);
    }

    @Override
    public void deleteRole(String roleName) {
        RoleEntity role = findRoleByName(roleName);
        roleRepository.delete(role);
    }

    @Override
    public PaginationResult<RoleDTO> getRoles() {
        List<RoleEntity> roles = roleRepository.findAll();

        List<RoleDTO> roleDTOs = roles.stream()
                .map(role -> mapper.map(role, RoleDTO.class))
                .toList();

        PaginationResult<RoleDTO> result = new PaginationResult<>();
        result.setData(roleDTOs);
        result.setTotalPages(1);
        result.setTotalItems((long) roles.size());

        return result;
    }
}
