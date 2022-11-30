package com.yang.springbootaliyunoss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @ClassName: FileEnum
 * @Author: XQ-Yang
 * @Date: 2022/11/30 0030
 * @Description:
 **/
@Getter
@AllArgsConstructor
public enum FileEnum {
    //文档
    DOCUMENT("文档", "/document/"),
    //图片
    PICTURE("图片", "/picture/"),
    //视频
    VIDEO("视频", "/video/"),
    //音频
    AUDIO("音频", "/audio/"),
    //压缩
    COMPRESS("压缩包", "/compress/");

    private final String name;
    private final String typePath;


    public static FileEnum nameOf(final String typePath) {
        return Arrays.stream(FileEnum.values())
                .filter(enu -> enu.getTypePath().equals(typePath))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("错误的文件类型"));

        // 这种写法需要在调用时判断是否为null
//        for (FileEnum fileEnum : FileEnum.values()) {
//            if (fileEnum.getTypePath().equals(typePath)) {
//                return fileEnum;
//            }
//        }
//        return null;
    }

    public static FileEnum typePathOf(final String name) {
        return Arrays.stream(FileEnum.values())
                .filter(enu -> enu.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("错误的文件类型"));
    }

}
