package com.taskManagerSystem.TaskManagerSystem.repositories;

import com.taskManagerSystem.TaskManagerSystem.entities.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, String> {

    Optional<PermissionEntity> findByName(String name);
}
