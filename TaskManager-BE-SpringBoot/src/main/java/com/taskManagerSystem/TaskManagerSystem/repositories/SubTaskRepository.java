package com.taskManagerSystem.TaskManagerSystem.repositories;

import com.taskManagerSystem.TaskManagerSystem.entities.SubTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTaskEntity, Long> {
}
