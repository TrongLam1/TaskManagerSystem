package com.taskManagerSystem.TaskManagerSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TaskManagerSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskManagerSystemApplication.class, args);
    }
}
