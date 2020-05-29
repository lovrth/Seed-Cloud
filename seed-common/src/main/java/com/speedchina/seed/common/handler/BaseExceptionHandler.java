package com.speedchina.seed.common.handler;


import com.speedchina.seed.common.entity.SeedResponse;
import com.speedchina.seed.common.exception.SeedAuthException;
import com.speedchina.seed.common.exception.SeedException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

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

    @ExceptionHandler(value = SeedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public SeedResponse handleFebsException(SeedException e) {
        log.error("系统错误", e);
        return new SeedResponse().message(e.getMessage());
    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return SeedResponse
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SeedResponse handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new SeedResponse().message(message.toString());
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return SeedResponse
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SeedResponse handleBindException(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new SeedResponse().message(message.toString());
    }
}