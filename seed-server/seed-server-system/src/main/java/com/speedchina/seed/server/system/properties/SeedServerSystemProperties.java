package com.speedchina.seed.server.system.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;


@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:seed-server-system.properties"})
@ConfigurationProperties(prefix = "seed.server.system")
public class SeedServerSystemProperties {

    /**
     * 免认证 URI，多个值的话以逗号分隔
     */
    private String anonUrl;

    private SeedSwaggerProperties swagger = new SeedSwaggerProperties();
}
