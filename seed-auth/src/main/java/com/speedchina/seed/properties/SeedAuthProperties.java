package com.speedchina.seed.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * 绑定seed-auth.properties的变量
 */
@Data
@SpringBootConfiguration // 注入IOC容器
@PropertySource(value = {"classpath:seed-auth.properties"})
@ConfigurationProperties(prefix = "seed.auth")
public class SeedAuthProperties {

    /** 数组形式绑定 */
    private SeedClientsProperties[] clients = {};
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;

    /** 免认证路径 */
    private String anonUrl;

    /** 对象形式绑定 */
    private SeedValidateCodeProperties code = new SeedValidateCodeProperties();
}
