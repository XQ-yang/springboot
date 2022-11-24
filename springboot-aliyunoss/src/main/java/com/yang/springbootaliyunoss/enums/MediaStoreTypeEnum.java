package com.yang.springbootaliyunoss.enums;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @ClassName: MediaStoreTypeEnum
 * @Author: XQ-Yang
 * @Date: 2022/11/24 0024
 * @Description:
 **/
@Getter
@AllArgsConstructor
public enum MediaStoreTypeEnum {
    /**
     * 本地存储
     */
    LOCAL(1,"本地存储"),

    /**
     * 阿里云OSS存储
     */
    ALIYUNOSS(2,"阿里云OSS存储");

    private final Integer code;
    private final String type;

    /**
     * 根据 枚举类型值 获取对应类型枚举
     *
     * @param value
     * @return
     */
    public static MediaStoreTypeEnum valueOf(final Integer value) {
        MediaStoreTypeEnum[] mediaStoreTypeEnums = MediaStoreTypeEnum.values();
        for (MediaStoreTypeEnum mediaStoreTypeEnum : mediaStoreTypeEnums) {
            if (mediaStoreTypeEnum.getCode().equals(value)) {
                return mediaStoreTypeEnum;
            }
        }
        return null;
    }

    public static Integer codeOf(final String type) {
        MediaStoreTypeEnum[] mediaStoreTypeEnums = MediaStoreTypeEnum.values();
        for (MediaStoreTypeEnum mediaStoreTypeEnum : mediaStoreTypeEnums) {
            if (mediaStoreTypeEnum.getType().equals(type)) {
                return mediaStoreTypeEnum.code;
            }
        }
        return null;
    }



}
