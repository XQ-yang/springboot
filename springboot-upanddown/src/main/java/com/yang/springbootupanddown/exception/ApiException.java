package com.yang.springbootupanddown.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 小强
 * @date: 2022/11/17 0017
 * @IDE: IntelliJ IDEA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiException extends RuntimeException{
    private String code;
    private String msg;

}
