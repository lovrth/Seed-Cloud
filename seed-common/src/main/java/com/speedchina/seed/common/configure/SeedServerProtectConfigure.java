package com.speedchina.seed.common.configure;


import com.speedchina.seed.common.interceptor.SeedServerProtectInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 让SeedServerProtectInterceptor过滤器生效我们需要定义一个配置类来将它注册到Spring IOC容器中
 */
public class SeedServerProtectConfigure implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor seedServerProtectInterceptor() {
        return new SeedServerProtectInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(seedServerProtectInterceptor());
    }

    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)// 当IOC容器里没有PasswordEncoder类型的Bean的时候，则将BCryptPasswordEncoder注册到IOC容器中
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
