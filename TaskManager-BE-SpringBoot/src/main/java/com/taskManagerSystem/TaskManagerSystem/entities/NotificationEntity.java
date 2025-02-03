package com.taskManagerSystem.TaskManagerSystem.entities;

import com.taskManagerSystem.TaskManagerSystem.enums.NotifyType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private NotifyType type = NotifyType.ALERT;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;

    @ManyToMany
    @JoinTable(
            name = "notify_team",
            joinColumns = @JoinColumn(name = "notify_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> team;
}
