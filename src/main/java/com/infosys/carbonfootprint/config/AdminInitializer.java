package com.infosys.carbonfootprint.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.infosys.carbonfootprint.entity.User;
import com.infosys.carbonfootprint.repository.UserRepository;

@Configuration
public class AdminInitializer {

    @Bean
    CommandLineRunner createAdmin(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder) {

        return args -> {

            if (userRepository.findByUsername("admin").isEmpty()) {

                User admin = new User();

                admin.setUsername("admin");
                admin.setEmail("admin@carbon.com");
                admin.setPassword(
                    passwordEncoder.encode("Admin@12345")
                );
                admin.setRole("ADMIN");
                admin.setStatus("APPROVED");
                admin.setFirstLogin(false);

                userRepository.save(admin);
            }
        };
    }
}
