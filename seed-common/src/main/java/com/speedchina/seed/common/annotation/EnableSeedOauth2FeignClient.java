package com.speedchina.seed.common.annotation;

import com.speedchina.seed.common.configure.SeedOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启带令牌的Feign请求，避免微服务内部调用出现401异常
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SeedOAuth2FeignConfigure.class)
public @interface EnableSeedOauth2FeignClient {
}
