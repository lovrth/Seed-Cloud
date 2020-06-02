package com.speedchina.seed.server.system.configure;

import com.speedchina.seed.common.handler.SeedAccessDeniedHandler;
import com.speedchina.seed.common.handler.SeedAuthExceptionEntryPoint;
import com.speedchina.seed.server.system.properties.SeedServerSystemProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 只有通过认证服务器发放的令牌才能进行访问
 * @author suyuan
 * @date 2020/5/25 18:34
 */
@Configuration
@EnableResourceServer
public class SeedServerSystemResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private SeedAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private SeedAuthExceptionEntryPoint exceptionEntryPoint;
    @Autowired
    private SeedServerSystemProperties properties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()//添加免认证路径配置
                .antMatchers("/**").authenticated();
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