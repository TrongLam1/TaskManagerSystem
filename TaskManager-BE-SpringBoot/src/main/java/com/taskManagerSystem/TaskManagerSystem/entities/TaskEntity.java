package com.taskManagerSystem.TaskManagerSystem.entities;

import com.taskManagerSystem.TaskManagerSystem.enums.TaskPriority;
import com.taskManagerSystem.TaskManagerSystem.enums.TaskStage;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column
    private String title;

    @Column
    private LocalDateTime date;

    @Column
    @Enumerated(EnumType.STRING)
    private TaskPriority taskPriority;

    @Column
    @Enumerated(EnumType.STRING)
    private TaskStage stage;

    @Column
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActivityEntity> activities;

    @Column
    private String subTask;

    @ElementCollection
    @OrderColumn
    private List<String> assets;

    @ManyToMany
    @JoinTable(
            name = "task_team",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> team;

    @Column
    private boolean isRemoved = false;
}
