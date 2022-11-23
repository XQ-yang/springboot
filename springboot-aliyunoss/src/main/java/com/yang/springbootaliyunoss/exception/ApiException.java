package com.yang.springbootaliyunoss.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ApiException
 * @Author: XQ-Yang
 * @Date: 2022/11/23 0023
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiException extends RuntimeException {
    private String code;
    private String msg;
}
