package com.taskManagerSystem.TaskManagerSystem.entities;

import com.taskManagerSystem.TaskManagerSystem.enums.TaskActivity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    @Enumerated(EnumType.STRING)
    private TaskActivity type = TaskActivity.ASSIGNED;

    @Column
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity by;
}
