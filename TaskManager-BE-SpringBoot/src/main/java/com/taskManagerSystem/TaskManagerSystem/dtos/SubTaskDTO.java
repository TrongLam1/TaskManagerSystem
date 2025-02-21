package com.taskManagerSystem.TaskManagerSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubTaskDTO {

    private Long subTaskId;

    private LocalDateTime createdAt;

    private String content;

    private String title;
}
