package com.taskManagerSystem.TaskManagerSystem.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long userId;

    private String name;

    private String title;

    private String email;
}
