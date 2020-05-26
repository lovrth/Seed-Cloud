package com.speedchina.seed.common.configure;


import com.speedchina.seed.common.handler.SeedAccessDeniedHandler;
import com.speedchina.seed.common.handler.SeedAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * 由于common项目不是springboot项目，
 * 所以不能用@Component注解注册到IOC容器中
 * 可以使用@Enable模块驱动的方式来解决这个问题
 * @author suyuan
 * @date 2020/5/26 9:51
 */
public class SeedAuthExceptionConfigure
{

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public SeedAccessDeniedHandler accessDeniedHandler() {
        return new SeedAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public SeedAuthExceptionEntryPoint authenticationEntryPoint() {
        return new SeedAuthExceptionEntryPoint();
    }
}
