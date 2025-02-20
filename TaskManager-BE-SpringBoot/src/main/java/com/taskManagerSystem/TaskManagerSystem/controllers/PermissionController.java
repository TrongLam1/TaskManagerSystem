package com.taskManagerSystem.TaskManagerSystem.controllers;

import com.taskManagerSystem.TaskManagerSystem.dtos.PermissionDTO;
import com.taskManagerSystem.TaskManagerSystem.requests.authorization.PermissionRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.PaginationResult;
import com.taskManagerSystem.TaskManagerSystem.responses.ResponseData;
import com.taskManagerSystem.TaskManagerSystem.services.impl.PermissionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Permission Controller")
public class PermissionController {
    
    private final PermissionServiceImpl permissionService;

    @Operation(summary = "Create new permission.")
    @PostMapping("/create")
    public ResponseData<PermissionDTO> createPermission(@RequestBody PermissionRequest request) {
        try {
            log.info("Request: Create new permission");
            return new ResponseData<>(HttpStatus.CREATED.value(), "Create new permission",
                    permissionService.createPermission(request));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Operation(summary = "Update permission.", description = "Update permission's description.")
    @PostMapping("/update")
    public ResponseData<PermissionDTO> updatePermission(@RequestBody PermissionRequest request) {
        try {
            log.info("Request: Update permission {}", request.getName());
            return new ResponseData<>(HttpStatus.CREATED.value(), "Update permission",
                    permissionService.updatePermission(request));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Operation(summary = "Get all permissions.")
    @GetMapping("/get-all")
    public ResponseData<PaginationResult<PermissionDTO>> getPermissions() {
        try {
            log.info("Request: Get all permissions.");
            return new ResponseData<>(HttpStatus.CREATED.value(), "Get all permissions",
                    permissionService.getPermissions());
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Operation(summary = "Delete permission.")
    @DeleteMapping("/delete")
    public ResponseData<String> deletePermission(@RequestParam("permissionName") String permissionName) {
        try {
            log.info("Request: Delete permission {}", permissionName);
            permissionService.deletePermission(permissionName);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Delete permission successfully.");
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
