package com.speedchina.seed.common.handler;


import com.speedchina.seed.common.entity.SeedResponse;
import com.speedchina.seed.common.exception.SeedAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局处理Controller层抛出来的异常
 * @author suyuan
 * @date 2020/5/26 11:32
 */
@Slf4j
public class BaseExceptionHandler
{

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
    public SeedResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return new SeedResponse().message("系统内部异常");
    }

    @ExceptionHandler(value = SeedAuthException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
    public SeedResponse handleSeedAuthException(SeedAuthException e) {
        log.error("系统错误", e);
        return new SeedResponse().message(e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) //403
    public SeedResponse handleAccessDeniedException(){
        return new SeedResponse().message("没有权限访问该资源");
    }
}