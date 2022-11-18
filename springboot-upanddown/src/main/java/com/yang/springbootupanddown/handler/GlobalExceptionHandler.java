package com.yang.springbootupanddown.handler;

import com.yang.springbootupanddown.exception.ApiException;
import com.yang.springbootupanddown.util.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: 小强
 * @date: 2022/11/17 0017
 * @IDE: IntelliJ IDEA
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public Result<Boolean> apiExceptionHandler(ApiException e) {
        Result<Boolean> result = new Result<>();
        result.setCode(e.getCode());
        result.setMsg(e.getMsg());
        return result;
    }
}
