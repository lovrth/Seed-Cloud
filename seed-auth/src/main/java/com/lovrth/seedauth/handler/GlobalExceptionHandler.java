package com.lovrth.seedauth.handler;


import com.speedchina.seed.common.handler.BaseExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局自定义异常
 * @author suyuan
 * @date 2020/5/26 11:37
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseExceptionHandler
{
}
