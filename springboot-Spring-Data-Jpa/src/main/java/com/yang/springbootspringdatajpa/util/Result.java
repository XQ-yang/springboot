package com.yang.springbootspringdatajpa.util;

import com.yang.springbootspringdatajpa.commenum.MessageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Result
 * @Author: XQ-Yang
 * @Date: 2022/11/17 0017
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(MessageEnum.SUCCESS.getCode(), MessageEnum.SUCCESS.getMsg(), data);
    }

    public static <T> Result<T> error() {
        return error(MessageEnum.ERROR);
    }

    public static <T> Result<T> error(MessageEnum messageEnum) {
        return new Result<>(messageEnum.getCode(), messageEnum.getMsg(), null);
    }

    public static <T> Result<T> error(String msg) {
        return error(MessageEnum.ERROR.getCode(),msg);
    }

    public static <T> Result<T> error(String code, String msg) {
        return new Result<>(code, msg, null);
    }
}
