package com.casaculturatungurahua.api;

import com.casaculturatungurahua.api.entities.Favorites;
import com.casaculturatungurahua.api.entities.User;
import com.casaculturatungurahua.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User admin = new User(1L, "admin", passwordEncoder.encode("admin"));
        if(!userRepository.existsByUsername(admin.getUsername())){
            userRepository.save(admin);
        }
    }


}
