package com.lovrth.seedauth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * Auth相关的配置类
 * @author suyuan
 * @date 2020/5/26 8:55
 */
@Data
@SpringBootConfiguration//@Component的派生注解
@PropertySource(value = {"classpath:seed-auth.properties"})//指定读取的配置文件路径
@ConfigurationProperties(prefix = "seed.auth")//指定了要读取的属性的统一前缀名称
public class SeedAuthProperties
{

    private SeedClientsProperties[] clients = {};
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;
}
