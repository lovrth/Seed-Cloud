package com.speedchina.seed.configure;

import com.speedchina.seed.common.handler.SeedAccessDeniedHandler;
import com.speedchina.seed.common.handler.SeedAuthExceptionEntryPoint;
import com.speedchina.seed.properties.SeedAuthProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器的配置类，对所有路径处理
 * 用于处理非/oauth/开头的请求，其主要用于资源的保护，客户端只能通过OAuth2协议发放的令牌来从资源服务器中获取受保护的资源
 * @author suyuan
 * @date 2020/5/25 16:13 
 */
@Configuration
@EnableResourceServer
public class SeedResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private SeedAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private SeedAuthExceptionEntryPoint exceptionEntryPoint;
    @Autowired
    private SeedAuthProperties properties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()//配置免认证资源
                .antMatchers("/**").authenticated()
                .and().httpBasic();
    }

    /**
     * 使用自定义异常
     * @author suyuan
     * @date 2020/5/26 10:00
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}
