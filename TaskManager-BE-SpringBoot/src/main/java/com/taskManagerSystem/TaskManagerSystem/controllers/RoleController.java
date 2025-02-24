package com.taskManagerSystem.TaskManagerSystem.controllers;

import com.taskManagerSystem.TaskManagerSystem.dtos.RoleDTO;
import com.taskManagerSystem.TaskManagerSystem.requests.authorization.RoleRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.PaginationResult;
import com.taskManagerSystem.TaskManagerSystem.responses.ResponseData;
import com.taskManagerSystem.TaskManagerSystem.services.impl.RoleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Role Controller")
public class RoleController {

    private final RoleServiceImpl roleService;

    @Operation(summary = "Create new role.")
    @PostMapping("/create")
    public ResponseData<RoleDTO> createRole(@RequestBody RoleRequest request) {
        try {
            log.info("Request: Create new role");
            return new ResponseData<>(HttpStatus.CREATED.value(), "Create new role",
                    roleService.createRole(request));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Operation(summary = "Update role.", description = "Update role's permissions.")
    @PostMapping("/update")
    public ResponseData<RoleDTO> updateRole(@RequestBody RoleRequest request) {
        try {
            log.info("Request: Update role {}", request.getName());
            return new ResponseData<>(HttpStatus.OK.value(), "Update role",
                    roleService.updateRole(request));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Operation(summary = "Get all roles.")
    @GetMapping("/get-all")
    public ResponseData<PaginationResult<RoleDTO>> getRoles() {
        try {
            log.info("Request: Get all roles.");
            return new ResponseData<>(HttpStatus.OK.value(), "Get all roles",
                    roleService.getRoles());
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Operation(summary = "Delete role.")
    @DeleteMapping("/delete")
    public ResponseData<String> deleteRole(@RequestParam("roleName") String roleName) {
        try {
            log.info("Request: Delete role {}", roleName);
            roleService.deleteRole(roleName);
            return new ResponseData<>(HttpStatus.OK.value(), "Delete role successfully.");
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
