package com.speedchina.seed.gateway.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域处理
 * @author suyuan
 * @date 2020/5/26 15:51
 */
@Configuration
public class SeedGateWayCorsConfigure
{
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);//允许cookie跨域；
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);//请求头部允许携带任何内容；
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);//允许任何来源；
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);//允许任何HTTP方法。
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
