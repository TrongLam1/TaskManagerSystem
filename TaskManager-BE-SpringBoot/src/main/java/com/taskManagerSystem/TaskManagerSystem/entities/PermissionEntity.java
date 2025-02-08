package com.taskManagerSystem.TaskManagerSystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionEntity {

    @Id
    private String name;

    private String description;
}
