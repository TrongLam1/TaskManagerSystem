package com.taskManagerSystem.TaskManagerSystem.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {
    private final Dotenv dotenv;

    public EnvConfig() {
        this.dotenv = Dotenv.load();
    }

    public String get(String key) {
        return dotenv.get(key);
    }
}

