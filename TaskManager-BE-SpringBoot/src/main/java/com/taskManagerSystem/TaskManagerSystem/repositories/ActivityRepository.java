package com.taskManagerSystem.TaskManagerSystem.repositories;

import com.taskManagerSystem.TaskManagerSystem.entities.ActivityEntity;
import com.taskManagerSystem.TaskManagerSystem.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {

    @Query("SELECT a FROM ActivityEntity a WHERE a.task = :task")
    List<ActivityEntity> findAllByTask(TaskEntity task);
}
