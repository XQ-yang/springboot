package com.yang.springbootaliyunoss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: ResponseCodeEnum
 * @Author: XQ-Yang
 * @Date: 2022/11/24 0024
 * @Description:
 **/
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {
    /**
     * 常见响应状态码
     */
    UN_KNOWN(-1000, "未知错误"),

    SUCCESS(2000,"请求成功"),

    INVALID_TOKEN(4001, "token无效"),

    ILLEGAL(4002, "参数非法"),

    NOT_FOUND(4003,"URL错误"),


    RESOURCES_NOT_EXIST(4004, "没有该资源"),

    FAIL(4005, "请求失败"),
    SERVER_ERROR(5000,"服务器错误"),

    BUSY(5001,"系统繁忙，请稍后重试");


    private final Integer code;
    private final String msg;




}
