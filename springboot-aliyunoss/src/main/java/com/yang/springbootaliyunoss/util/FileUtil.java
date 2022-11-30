package com.yang.springbootaliyunoss.util;

import com.yang.springbootaliyunoss.enums.FileEnum;
import com.yang.springbootaliyunoss.interfaces.Consts;

/**
 * @ClassName: FileUtil
 * @Author: XQ-Yang
 * @Date: 2022/11/30 0030
 * @Description:
 **/
public class FileUtil {
    public static String getFileType(String fileSuffix) {
        String typePath = "未知";

        if (Consts.DOCUMENT.contains(fileSuffix.toLowerCase())) {
            typePath = FileEnum.DOCUMENT.getTypePath();
        }
        if (Consts.PICTURE.contains(fileSuffix.toLowerCase())) {
            typePath = FileEnum.PICTURE.getTypePath();
        }
        if (Consts.AUDIO.contains(fileSuffix.toLowerCase())) {
            typePath = FileEnum.AUDIO.getTypePath();
        }
        if (Consts.VIDEO.contains(fileSuffix.toLowerCase())) {
            typePath = FileEnum.VIDEO.getTypePath();
        }
        if (Consts.COMPRESS.contains(fileSuffix.toLowerCase())) {
            typePath = FileEnum.COMPRESS.getTypePath();
        }

        return typePath;
    }
}
