package com.yang.springbootaliyunoss.exception;

import com.yang.springbootaliyunoss.enums.ResponseCodeEnum;
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
    private ResponseCodeEnum responseCodeEnum;
}
