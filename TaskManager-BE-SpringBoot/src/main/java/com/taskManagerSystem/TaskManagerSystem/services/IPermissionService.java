package com.taskManagerSystem.TaskManagerSystem.services;

import com.taskManagerSystem.TaskManagerSystem.dtos.PermissionDTO;
import com.taskManagerSystem.TaskManagerSystem.requests.authorization.PermissionRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.PaginationResult;

public interface IPermissionService {

    PermissionDTO createPermission(PermissionRequest request);

    PermissionDTO updatePermission(PermissionRequest request);

    void deletePermission(String permission);

    PaginationResult<PermissionDTO> getPermissions();
}
