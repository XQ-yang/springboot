package com.yang.springbootspringdatajpa.exceptionhandler;

import com.yang.springbootspringdatajpa.commenum.MessageEnum;
import com.yang.springbootspringdatajpa.exception.ApiException;
import com.yang.springbootspringdatajpa.util.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName: GlobalExceptionHandler
 * @Author: XQ-Yang
 * @Date: 2022/11/17 0017
 * @Description:
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result<Boolean> globalException(Exception e) {
        Result<Boolean> result = new Result<>();
        result.setCode(MessageEnum.ERROR.getCode());
        result.setMsg(e.getMessage() == null ? MessageEnum.ERROR.getMsg() : e.getMessage());
        return result;
    }

    @ExceptionHandler(ApiException.class)
    public Result<Boolean> apiException(ApiException e) {
        Result<Boolean> result = new Result<>();
        result.setCode(e.getCode());
        result.setMsg(e.getMsg());
        return result;
    }
}
