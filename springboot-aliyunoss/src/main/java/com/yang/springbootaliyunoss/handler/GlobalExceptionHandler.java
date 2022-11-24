package com.yang.springbootaliyunoss.handler;

import com.yang.springbootaliyunoss.enums.ResponseCodeEnum;
import com.yang.springbootaliyunoss.exception.ApiException;
import com.yang.springbootaliyunoss.util.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

/**
 * @ClassName: GlobalExceptionHandler
 * @Author: XQ-Yang
 * @Date: 2022/11/23 0023
 * @Description:
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public Result<Boolean> apiExceptionHandler(ResponseCodeEnum responseCodeEnum) {
        Result<Boolean> result = new Result<>();
        result.setCode(responseCodeEnum.getCode());
        result.setMsg(responseCodeEnum.getMsg());
        return result;
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<Boolean> RuntimeExceptionHandler(ResponseCodeEnum responseCodeEnum) {
        Result<Boolean> result = new Result<>();
        result.setCode(responseCodeEnum.getCode());
        result.setMsg(responseCodeEnum.getMsg());
        return result;
    }
}
