package com.speedchina.seed.common.annotation;


import com.speedchina.seed.common.configure.SeedAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 认证类型异常响应 401 403
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SeedAuthExceptionConfigure.class)
public @interface EnableSeedAuthExceptionHandler {
}