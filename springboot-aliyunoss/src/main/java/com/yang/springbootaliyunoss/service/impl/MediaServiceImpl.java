package com.yang.springbootaliyunoss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.springbootaliyunoss.entity.AliyunOssProperties;
import com.yang.springbootaliyunoss.entity.Media;
import com.yang.springbootaliyunoss.mapper.MediaMapper;
import com.yang.springbootaliyunoss.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

import static cn.hutool.crypto.SecureUtil.md5;

/**
 * @author 小强
 * @description 针对表【tb_file】的数据库操作Service实现
 * @createDate 2022-11-22 19:51:25
 */
@Service
public class MediaServiceImpl extends ServiceImpl<MediaMapper, Media>
        implements MediaService {
    @Autowired
    private AliyunOssProperties aliyunOssProperties;

    @Override
    public String uploadFile(MultipartFile file) {
        OSS ossClient = aliyunOssProperties.ossClient();
        String endpoint = aliyunOssProperties.getEndpoint();
        String bucket = aliyunOssProperties.getBucket();
        String filehost = aliyunOssProperties.getFilehost();
        // 文件原始名称
        String originalFilename = file.getOriginalFilename();
        // 文件类型
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String type = suffix.replace(".", "");
        // 文件大小
        Long size = file.getSize();

        // 使用UUID随机生成新文件名
        String newFileName = UUID.randomUUID().toString().replace("-", "") + suffix;


        try {
            // 文件的md5
            String md5 = md5(file.getInputStream());
            PutObjectResult result = ossClient.putObject(bucket, filehost + "/" + newFileName, file.getInputStream());
            if (result != null) {
                String url = "https://" + bucket + "." + endpoint + "/" + filehost + "/" + newFileName;
                Media media = new Media();
                media.setName(originalFilename);
                media.setType(type);
                media.setSize(size);
                media.setMd5(md5);
                media.setUrl(url);
                media.setNewName(newFileName);

                baseMapper.insert(media);
                return url;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            ossClient.shutdown();
        }

        return null;
    }


    @Override
    public void download(String fileName, HttpServletResponse response) {
        OSS ossClient = aliyunOssProperties.ossClient();
        String bucket = aliyunOssProperties.getBucket();
        // 获取文件原始名称
        String originalFileName;
        Media media = getFileName(fileName);
        if (media != null) {
            originalFileName = media.getName();
        } else {
            throw new RuntimeException("没有该对象");
        }
        // 设置响应编码
        response.setCharacterEncoding("UTF-8");

        try {
            // 设置响应头、以附件形式打开文件
            response.setHeader("content-disposition", "attachment; fileName=" + URLEncoder.encode(originalFileName, "UTF-8"));

            OSSObject ossObject = ossClient.getObject(bucket, fileName);
            // 读取文件内容
            BufferedInputStream inputStream = new BufferedInputStream(ossObject.getObjectContent());
            BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());

            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
            }

            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Media getFileName(String fileName) {
        LambdaQueryWrapper<Media> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Media::getName).eq(Media::getNewName, fileName);
        return baseMapper.selectOne(queryWrapper);
    }
}




