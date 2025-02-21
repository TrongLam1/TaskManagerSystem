package com.taskManagerSystem.TaskManagerSystem.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "sub_tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class SubTaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subTaskId;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    private String content;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;
}
