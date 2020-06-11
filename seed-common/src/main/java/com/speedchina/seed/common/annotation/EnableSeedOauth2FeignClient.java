package com.speedchina.seed.common.annotation;

import com.speedchina.seed.common.configure.SeedOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 将SeedOAuth2FeignConfigure类注入
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SeedOAuth2FeignConfigure.class)
public @interface EnableSeedOauth2FeignClient {
}
