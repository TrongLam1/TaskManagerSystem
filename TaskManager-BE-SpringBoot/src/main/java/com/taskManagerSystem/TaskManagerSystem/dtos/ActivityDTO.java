package com.taskManagerSystem.TaskManagerSystem.dtos;

import com.taskManagerSystem.TaskManagerSystem.enums.TaskActivity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityDTO {

    private Long activityId;

    private TaskActivity type;

    private LocalDateTime date;

    private UserDTO by;
}
