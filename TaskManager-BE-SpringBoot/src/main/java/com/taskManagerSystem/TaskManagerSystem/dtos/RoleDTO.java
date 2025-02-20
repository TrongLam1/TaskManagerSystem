package com.taskManagerSystem.TaskManagerSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    private String name;

    private Set<PermissionDTO> permissions;
}
