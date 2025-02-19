package com.taskManagerSystem.TaskManagerSystem.repositories;

import com.taskManagerSystem.TaskManagerSystem.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    Optional<RoleEntity> findByName(String name);
}
