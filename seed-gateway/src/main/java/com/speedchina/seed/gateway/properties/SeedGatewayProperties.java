package com.speedchina.seed.gateway.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * 限制通过网关访问 ** /actuator/**资源
 * @author suyuan
 * @date 2020/6/2 11:24
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:seed-gateway.properties"})
@ConfigurationProperties(prefix = "seed.gateway")
public class SeedGatewayProperties
{
    /**
     * 禁止外部访问的 URI，多个值的话以逗号分隔
     */
    private String forbidRequestUri;
}
