package com.speedchina.seed.common.annotation;


import com.speedchina.seed.common.configure.SeedAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 用自定义注解来设置上自定义异常
 * @author suyuan
 * @date 2020/5/26 9:53
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SeedAuthExceptionConfigure.class)
public @interface EnableSeedAuthExceptionHandler
{

}