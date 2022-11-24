package com.yang.springbootaliyunoss.util;

import com.yang.springbootaliyunoss.enums.ResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 小强
 * @date: 2022/11/14 0014
 * @IDE: IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg(),data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(ResponseCodeEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(ResponseCodeEnum.SERVER_ERROR.getCode(), msg, null);
    }

    public static <T> Result<T> error() {
        return new Result<>(ResponseCodeEnum.UN_KNOWN.getCode(), ResponseCodeEnum.UN_KNOWN.getMsg(), null);
    }
}
