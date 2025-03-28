package com.taskManagerSystem.TaskManagerSystem.entities;

import com.taskManagerSystem.TaskManagerSystem.enums.TaskActivity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    @Enumerated(EnumType.STRING)
    private TaskActivity activity;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity by;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;
}
