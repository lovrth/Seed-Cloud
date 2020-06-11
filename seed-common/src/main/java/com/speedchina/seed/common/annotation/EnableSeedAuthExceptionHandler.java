package com.speedchina.seed.common.annotation;


import com.speedchina.seed.common.configure.SeedAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 用自定义注解来设置上自定义异常
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SeedAuthExceptionConfigure.class)
public @interface EnableSeedAuthExceptionHandler {
}