package com.yang.springbootupanddown.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.springbootupanddown.entity.FileModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @author 小强
* @description 针对表【tb_file】的数据库操作Service
* @createDate 2022-11-15 20:07:46
*/
public interface FileModelService extends IService<FileModel> {

    /**
     * 文件上传
     * @param uploadPath
     * @param file
     * @throws IOException
     * @return
     */
    String upload(String uploadPath, MultipartFile file) throws IOException;

    /**
     * 文件下载
     * @param filePath
     * @param fileName
     * @param response
     * @throws IOException
     */
    void download(String filePath, String fileName, HttpServletResponse response) throws IOException;

    /**
     * 根据md5获取文件信息
     * @param md5
     * @return
     */
    FileModel getFileByMD5(String md5);

    /**
     * 根据id获取file
     * @param id
     * @return
     */
    FileModel getFileById(Integer id);
}
