package com.speedchina.seed.monitor.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class SeedMonitorAdminApplication
{

    public static void main(String[] args) {
        SpringApplication.run(SeedMonitorAdminApplication.class, args);
    }

}
