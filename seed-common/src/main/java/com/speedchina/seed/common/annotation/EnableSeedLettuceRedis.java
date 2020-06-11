package com.speedchina.seed.common.annotation;

import com.speedchina.seed.common.configure.SeedLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SeedLettuceRedisConfigure.class)
public @interface EnableSeedLettuceRedis {
}