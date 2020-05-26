package com.lovrth.seedauth;

import com.speedchina.seed.common.annotation.EnableSeedAuthExceptionHandler;
import com.speedchina.seed.common.annotation.EnableSeedServerProtect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableSeedAuthExceptionHandler//自定义异常
@EnableSeedServerProtect
public class SeedAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeedAuthApplication.class, args);
    }

}
