package com.yang.springbootspringdatajpa.commenum;

import lombok.Getter;

/**
 * @ClassName: MessageEnum
 * @Author: XQ-Yang
 * @Date: 2022/11/17 0017
 * @Description:
 **/
@Getter
public enum MessageEnum {
    SUCCESS("200","操作成功！"),
    NOT_FOUND("404", "没有该对象"),
    ERROR("500","系统错误！");

    MessageEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final String code;
    private final String msg;
}
