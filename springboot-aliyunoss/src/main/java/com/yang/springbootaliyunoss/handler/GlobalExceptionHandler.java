package com.yang.springbootaliyunoss.handler;

import com.yang.springbootaliyunoss.exception.ApiException;
import com.yang.springbootaliyunoss.util.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.yang.springbootaliyunoss.util.Result.error;

/**
 * @ClassName: GlobalExceptionHandler
 * @Author: XQ-Yang
 * @Date: 2022/11/23 0023
 * @Description:
 **/
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
