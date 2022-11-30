package com.yang.springbootaliyunoss.interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: Consts
 * @Author: XQ-Yang
 * @Date: 2022/11/30 0030
 * @Description:
 **/
public interface Consts {
    /**
     * 文档
     */
    List<String> DOCUMENT = Arrays.asList(".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".txt", ".pdf");
    /**
     * 图片
     */

    List<String> PICTURE = Arrays.asList(".jpg", ".png", ".jpeg", ".bmp", ".gif");
    /**
     * 视频
     */
    List<String> VIDEO = Arrays.asList(".avi", ".mp4", ".rmvb", ".flv", ".wmv");
    /**
     * 音频
     */
    List<String> AUDIO = Arrays.asList(".mp3", ".wav", ".amr");
    /**
     * 压缩
     */
    List<String> COMPRESS = Arrays.asList(".zip", ".rar", ".7z");



}
