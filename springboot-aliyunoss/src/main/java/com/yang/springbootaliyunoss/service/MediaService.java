package com.yang.springbootaliyunoss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.springbootaliyunoss.entity.Media;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
* @author 小强
* @description 针对表【tb_file】的数据库操作Service
* @createDate 2022-11-22 19:51:25
*/
public interface MediaService extends IService<Media> {
    /**
     * aliyunOSS上传文件
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);

    /**
     * 文件下载
     * @param fileName
     * @param response
     */
    void download(String fileName, HttpServletResponse response);


    /**
     * 获取文件原始名称
     * @param fileName
     * @return
     */
    Media getFileName(String fileName);
}
