package com.speedchina.seed.common.annotation;

import com.speedchina.seed.common.configure.SeedServerProtectConfigure;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

/**
 * 开启微服务防护，避免客户端绕过网关直接请求微服务
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SeedServerProtectConfigure.class)
public @interface EnableSeedServerProtect {
}