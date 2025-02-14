package com.taskManagerSystem.TaskManagerSystem.repositories;

import com.taskManagerSystem.TaskManagerSystem.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.isActive = :isActive")
    Page<UserEntity> findAllUser(@Param("isActive") boolean isActive, Pageable pageable);
}
