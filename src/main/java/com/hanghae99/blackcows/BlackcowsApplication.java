package com.hanghae99.blackcows;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BlackcowsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlackcowsApplication.class, args);
    }

}