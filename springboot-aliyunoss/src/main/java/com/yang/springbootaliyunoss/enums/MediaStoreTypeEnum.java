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
        return Arrays.stream(MediaStoreTypeEnum.values())
                .filter(enu -> enu.getCode().equals(value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("错误的资源存储方式"));

//        MediaStoreTypeEnum[] mediaStoreTypeEnums = MediaStoreTypeEnum.values();
//        for (MediaStoreTypeEnum mediaStoreTypeEnum : mediaStoreTypeEnums) {
//            if (mediaStoreTypeEnum.getCode().equals(value)) {
//                return mediaStoreTypeEnum;
//            }
//        }
//        return null;
    }

    public static MediaStoreTypeEnum codeOf(final String type) {
        return Arrays.stream(MediaStoreTypeEnum.values())
                .filter(enu -> enu.getType().equals(type))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("错误的资源存储方式"));


//        MediaStoreTypeEnum[] mediaStoreTypeEnums = MediaStoreTypeEnum.values();
//        for (MediaStoreTypeEnum mediaStoreTypeEnum : mediaStoreTypeEnums) {
//            if (mediaStoreTypeEnum.getType().equals(type)) {
//                return mediaStoreTypeEnum;
//            }
//        }
//        return null;
    }



}
