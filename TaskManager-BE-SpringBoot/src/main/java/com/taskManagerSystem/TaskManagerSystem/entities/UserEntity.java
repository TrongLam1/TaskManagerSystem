package com.taskManagerSystem.TaskManagerSystem.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column
    private String name;

    @Column
    private String title;

    @ManyToMany
    private Set<RoleEntity> roles;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String refreshToken;

    @ManyToMany(mappedBy = "team")
    private List<TaskEntity> tasks;

    @ManyToMany(mappedBy = "team")
    private List<NotificationEntity> notifies;

    @Column
    private boolean isActive;
}
