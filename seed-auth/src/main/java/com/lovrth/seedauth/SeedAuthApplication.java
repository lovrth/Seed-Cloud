package com.lovrth.seedauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SeedAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeedAuthApplication.class, args);
    }

}
