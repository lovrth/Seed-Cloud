package com.speedchina.seed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SeedRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeedRegisterApplication.class, args);
    }

}
