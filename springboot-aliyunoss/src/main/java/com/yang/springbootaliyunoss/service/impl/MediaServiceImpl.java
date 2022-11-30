package com.yang.springbootaliyunoss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.springbootaliyunoss.entity.AliyunOssProperties;
import com.yang.springbootaliyunoss.entity.Media;
import com.yang.springbootaliyunoss.enums.FileEnum;
import com.yang.springbootaliyunoss.enums.MediaStoreTypeEnum;
import com.yang.springbootaliyunoss.enums.ResponseCodeEnum;
import com.yang.springbootaliyunoss.exception.ApiException;
import com.yang.springbootaliyunoss.mapper.MediaMapper;
import com.yang.springbootaliyunoss.service.MediaService;
import com.yang.springbootaliyunoss.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
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
    private String endpoint;
    private String bucket;
    private String filehost;

    @Value("${local.uploadPath}")
    private String uploadPath;

    /**
     * java系统包自带的注解，标注的方法会在构造器调用完毕后执行一次
     */
    @PostConstruct
    public void init() {
        this.endpoint = aliyunOssProperties.getEndpoint();
        this.bucket = aliyunOssProperties.getBucket();
        this.filehost = aliyunOssProperties.getFilehost();
    }

    @Override
    public String uploadFile(MultipartFile file, MediaStoreTypeEnum mediaStoreTypeEnum, HttpServletRequest request) {
        OSS ossClient = aliyunOssProperties.ossClient();
        // 文件原始名称
        String originalFilename = file.getOriginalFilename();
        // 文件类型
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 文件分类路径
        String typePath = FileUtil.getFileType(suffix);

        String type = FileEnum.nameOf(typePath).getName();
        // 文件大小
        Long size = file.getSize();

        // 使用UUID随机生成新文件名
        String newFileName = UUID.randomUUID().toString().replace("-", "") + suffix;

        // 文件url
        String url = null;

        try {
            // 文件的md5
            String md5 = md5(file.getInputStream());

            switch (mediaStoreTypeEnum) {
                case LOCAL:
                    File dir = new File(uploadPath + typePath);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    file.transferTo(new File(uploadPath + typePath + originalFilename));
                    url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + typePath + originalFilename;
                    break;

                case ALIYUNOSS:
                    PutObjectResult result = ossClient.putObject(bucket, filehost + typePath + originalFilename, file.getInputStream());
                    if (result != null) {
                        url = "https://" + bucket + "." + endpoint + "/" + filehost + typePath + originalFilename;
                    }
                    break;

            }

            Media media = new Media();
            media.setName(originalFilename);
            media.setType(type);
            media.setSize(size);
            media.setMd5(md5);
            media.setStoreType(mediaStoreTypeEnum.getType());
            media.setUrl(url);
            media.setNewName(newFileName);

            baseMapper.insert(media);
            return url;
        } catch (IOException e) {
            throw new ApiException(ResponseCodeEnum.FAIL);
        } finally {
            ossClient.shutdown();
        }
    }


    @Override
    public String download(String fileName, String typePath, MediaStoreTypeEnum mediaStoreTypeEnum, HttpServletResponse response) throws IOException {

        // 设置响应编码
        response.setCharacterEncoding("UTF-8");

        // 设置响应头、以附件形式打开文件，URLEncoder.encode()防止中文文件名乱码
        response.setHeader("content-disposition", "attachment; fileName=" + URLEncoder.encode(fileName, "UTF-8"));

        String msg = "下载成功";
        switch (mediaStoreTypeEnum) {
            case LOCAL:
                msg = downloadLocal(fileName, typePath, response);
                break;
            case ALIYUNOSS:
                msg = downloadAliyunOss(fileName, typePath, response);
                break;
        }
        return msg;
    }

    @Override
    public Media getFileName(String fileName) {
        LambdaQueryWrapper<Media> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Media::getName)
                .eq(Media::getNewName, fileName)
                .eq(Media::getIsDeleted, 0);
        List<Media> mediaList = baseMapper.selectList(queryWrapper);
        return mediaList.size() == 0 ? null : mediaList.get(0);
    }

    public Media getFileByMd5(String md5) {
        LambdaQueryWrapper<Media> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Media::getMd5, md5)
                .eq(Media::getIsDeleted, 0);
        List<Media> mediaList = baseMapper.selectList(queryWrapper);
        return mediaList.size() == 0 ? null : mediaList.get(0);
    }

    @Override
    public Page<Media> mediaListByPage(Integer pageNum, Integer pageSize) {
        Page<Media> page = new Page<>(pageNum, pageSize);
        Page<Media> mediaPage = baseMapper.selectPage(page, null);
        return mediaPage;
    }

    public String downloadLocal(String fileName, String typePath, HttpServletResponse response) throws IOException {
        File file = new File(uploadPath + typePath + fileName);
        System.err.println(file.exists());
        if (file.exists()) {
            FileInputStream fileInputStream = null;
            ServletOutputStream outputStream = null;
            try {
                fileInputStream = new FileInputStream(uploadPath + typePath + fileName);
                outputStream = response.getOutputStream();
                byte[] bytes = new byte[1024];
                int length;
                while ((length = fileInputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, length);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "下载失败！";
            } finally {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            }
        } else {
            return "文件不存在";
        }
        return "下载成功";
    }


    public String downloadAliyunOss(String fileName, String typePath, HttpServletResponse response) {

        OSS ossClient = aliyunOssProperties.ossClient();
        OSSObject ossObject;

        boolean exist = ossClient.doesObjectExist(bucket, filehost + typePath + fileName);
        System.err.println(exist);
        if (!exist) {
            return "文件不存在！";
        }

        BufferedInputStream inputStream = null;
        BufferedOutputStream aliyunoutputStream = null;

        try {
            try {
                ossObject = ossClient.getObject(bucket, filehost + typePath + fileName);
            } catch (OSSException e) {
                e.printStackTrace();
                return "文件不存在！";
            } catch (ClientException e) {
                e.printStackTrace();
                return "bucket不存在！";
            }


            // 读取文件内容
            inputStream = new BufferedInputStream(ossObject.getObjectContent());
            aliyunoutputStream = new BufferedOutputStream(response.getOutputStream());

            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                aliyunoutputStream.write(bytes, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "下载失败！";
        } finally {
            try {
                aliyunoutputStream.flush();
                aliyunoutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return "下载成功";
    }
}




