package com.taskManagerSystem.TaskManagerSystem.services;

import com.taskManagerSystem.TaskManagerSystem.dtos.RoleDTO;
import com.taskManagerSystem.TaskManagerSystem.requests.authorization.RoleRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.PaginationResult;

public interface IRoleService {

    RoleDTO createRole(RoleRequest request);

    RoleDTO updateRole(RoleRequest request);

    void deleteRole(String roleName);

    PaginationResult<RoleDTO> getRoles();
}
