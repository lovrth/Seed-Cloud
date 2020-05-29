package com.speedchina.seed.common.exception;

/**
 * 系统通用业务异常
 * @author suyuan
 * @date 2020/5/28 16:12
 */
public class SeedException extends Exception{

    private static final long serialVersionUID = -6916154462432027437L;

    public SeedException(String message){
        super(message);
    }
}