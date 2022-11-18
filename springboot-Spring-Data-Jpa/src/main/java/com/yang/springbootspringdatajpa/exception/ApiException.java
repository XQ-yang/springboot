package com.yang.springbootspringdatajpa.exception;

import lombok.Data;

/**
 * @ClassName: Apiexception
 * @Author: XQ-Yang
 * @Date: 2022/11/17 0017
 * @Description:
 **/
@Data
public class ApiException extends RuntimeException {
    private String code;
    private String msg;

    public ApiException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
