package com.yang.springbootaliyunoss.util;

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
    private static final String SUCCESS_CODE = "200";
    private static final String ERROR_CODE = "400";
    private static final String UNKNOWN_CODE = "500";

    private String code;
    private String msg;
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(SUCCESS_CODE, "",null);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(SUCCESS_CODE, msg, data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(ERROR_CODE, msg, null);
    }

    public static <T> Result<T> error() {
        return new Result<>(UNKNOWN_CODE, "系统错误", null);
    }
}
