package com.taskManagerSystem.TaskManagerSystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String name;

    @Column
    private String title;

    @Column
    private String role;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private boolean isAdmin;

    @ManyToMany(mappedBy = "team")
    private List<TaskEntity> tasks;

    @ManyToMany(mappedBy = "team")
    private List<NotificationEntity> notifies;

    @Column
    private boolean isActive;
}
