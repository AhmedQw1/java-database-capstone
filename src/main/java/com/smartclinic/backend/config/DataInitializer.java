package com.smartclinic.backend.config;

import com.smartclinic.backend.models.User;
import com.smartclinic.backend.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create a default admin user if one doesn't exist
        if (userRepository.findByEmail("admin@smartclinic.com").isEmpty()) {
            User admin = new User();
            admin.setEmail("admin@smartclinic.com");
            admin.setPassword(passwordEncoder.encode("password123")); // Always encode passwords
            admin.setRole("ROLE_ADMIN");
            userRepository.save(admin);
            System.out.println("Default admin user created!");
        }
    }
}