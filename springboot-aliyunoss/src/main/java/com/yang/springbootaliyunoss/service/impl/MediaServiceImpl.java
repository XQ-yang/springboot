package com.yang.springbootaliyunoss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.springbootaliyunoss.entity.AliyunOssProperties;
import com.yang.springbootaliyunoss.entity.Media;
import com.yang.springbootaliyunoss.mapper.MediaMapper;
import com.yang.springbootaliyunoss.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        String accessKeyId = aliyunOssProperties.getAccessKeyId();
        String accessKeySecret = aliyunOssProperties.getAccessKeySecret();
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

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

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

    }
}




