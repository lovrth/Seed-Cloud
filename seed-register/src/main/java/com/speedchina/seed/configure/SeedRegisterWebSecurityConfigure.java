package com.speedchina.seed.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 开启Eureka服务端保护
 * @author lovrth
 * @since 2020/5/19
 */
@EnableWebSecurity
public class SeedRegisterWebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**")
                .and()
                .authorizeRequests().antMatchers("/actuator/**").permitAll();//将/actuator/**资源纳入到免认证路径中
        super.configure(http);
    }
}
