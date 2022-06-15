package com.hanghae99.blackcows;

import com.hanghae99.blackcows.configuration.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
@EnableConfigurationProperties(AppProperties.class)
public class BlackcowsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlackcowsApplication.class, args);
    }

}
