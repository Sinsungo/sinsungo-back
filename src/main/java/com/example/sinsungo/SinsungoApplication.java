package com.example.sinsungo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SinsungoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SinsungoApplication.class, args);
    }

}
