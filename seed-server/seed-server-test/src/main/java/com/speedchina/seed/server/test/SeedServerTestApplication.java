package com.speedchina.seed.server.test;

import com.speedchina.seed.common.annotation.EnableSeedAuthExceptionHandler;
import com.speedchina.seed.common.annotation.EnableSeedOauth2FeignClient;
import com.speedchina.seed.common.annotation.EnableSeedServerProtect;
import com.speedchina.seed.common.annotation.SeedCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启Spring Cloud Security权限注解
@SeedCloudApplication
public class SeedServerTestApplication
{

    public static void main(String[] args) {
        SpringApplication.run(SeedServerTestApplication.class, args);
    }

}
