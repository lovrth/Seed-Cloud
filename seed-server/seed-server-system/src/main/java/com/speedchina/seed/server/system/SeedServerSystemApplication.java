package com.speedchina.seed.server.system;

import com.speedchina.seed.common.annotation.EnableSeedAuthExceptionHandler;
import com.speedchina.seed.common.annotation.EnableSeedServerProtect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //事务
@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableSeedAuthExceptionHandler
@EnableSeedServerProtect
@MapperScan("com.speedchina.seed.server.system.mapper")
public class SeedServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeedServerSystemApplication.class, args);
    }

}
