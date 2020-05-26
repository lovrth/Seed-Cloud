package com.speedchina.seed.gateway.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 覆盖默认的。关闭csrf功能，否则会报csrf相关异常
 * @author suyuan
 * @date 2020/5/25 17:24
 */
@EnableWebSecurity
public class SeedGatewaySecurityConfigure extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}
