package com.yang.springbootaliyunoss.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.springbootaliyunoss.entity.Media;
import com.yang.springbootaliyunoss.enums.MediaStoreTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
* @author 小强
* @description 针对表【tb_file】的数据库操作Service
* @createDate 2022-11-22 19:51:25
*/
public interface MediaService extends IService<Media> {
    /**
     * aliyunOSS上传文件
     * @param file
     * @param mediaStoreTypeEnum
     * @param request
     * @return
     */
    String uploadFile(MultipartFile file, MediaStoreTypeEnum mediaStoreTypeEnum, HttpServletRequest request);

    /**
     * 文件下载
     * @param fileName
     * @param typePath
     * @param mediaStoreTypeEnum
     * @param response
     * @return
     * @throws IOException
     */
    String download(String fileName, String typePath, MediaStoreTypeEnum mediaStoreTypeEnum, HttpServletResponse response) throws IOException;


    /**
     * 获取文件原始名称
     * @param fileName
     * @return
     */
    Media getFileName(String fileName);

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<Media> mediaListByPage(Integer pageNum, Integer pageSize);
}
