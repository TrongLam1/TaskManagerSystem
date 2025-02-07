package com.taskManagerSystem.TaskManagerSystem.config;

import com.taskManagerSystem.TaskManagerSystem.entities.UserEntity;
import com.taskManagerSystem.TaskManagerSystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add("ADMIN");

                UserEntity admin = UserEntity.builder()
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("123456"))
                        .build();

                userRepository.save(admin);

                log.info("Created admin account.");
            }
        };
    }
}
