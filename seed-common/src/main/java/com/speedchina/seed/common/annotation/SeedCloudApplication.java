package com.speedchina.seed.common.annotation;

import com.speedchina.seed.common.selector.SeedCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableSeedAuthExceptionHandler
 * EnableSeedOauth2FeignClient
 * EnableSeedServerProtect
 * 三个注解的组合注解
 * @author suyuan
 * @date 2020/5/26 15:38
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SeedCloudApplicationSelector.class)
public @interface SeedCloudApplication
{

}
