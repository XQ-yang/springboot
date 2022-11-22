package com.yang.springbootupanddown.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.springbootupanddown.entity.FileModel;
import com.yang.springbootupanddown.mapper.FileModelMapper;
import com.yang.springbootupanddown.service.FileModelService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static cn.hutool.crypto.SecureUtil.md5;

/**
 * @author 小强
 * @description 针对表【tb_file】的数据库操作Service实现
 * @createDate 2022-11-15 20:07:46
 */
@Service
public class FileModelServiceImpl extends ServiceImpl<FileModelMapper, FileModel>
        implements FileModelService {

    @Override
    public String upload(String uploadPath, MultipartFile file) throws IOException {
        if (file == null) {
            return "没有选择文件";
        }
        // 文件原始名称
        String originalFilename = file.getOriginalFilename();

        // 文件类型
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String type = suffix.replace(".", "");

        // 文件大小
        long size = file.getSize();

        // 使用UUID生成新的文件名,防止覆盖
        String fileName = UUID.randomUUID().toString().replace("-", "") + suffix;

        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String url = uploadPath + fileName;

        // 文件md5
        String md5 = md5(file.getInputStream());
        String downloadUrl;

        FileModel fileByMD5 = getFileByMD5(md5);
        if (fileByMD5 != null) {
            downloadUrl = fileByMD5.getDownloadUrl();
        } else {
            file.transferTo(new File(uploadPath + fileName));
            downloadUrl = "http://localhost:8080/file/download?fileName=" + fileName;
        }

        // 保存到数据库
        FileModel fileModel = new FileModel();
        fileModel.setName(originalFilename);
        fileModel.setType(type);
        fileModel.setSize(size);
        fileModel.setMd5(md5);
        fileModel.setUrl(url);
        fileModel.setNewName(fileName);
        fileModel.setDownloadUrl(downloadUrl);
        baseMapper.insert(fileModel);

        return downloadUrl;
    }

    @Override
    public void download(String filePath, String fileName, HttpServletResponse response) throws IOException {
        // 设置响应编码
        response.setCharacterEncoding("UTF-8");

        // 设置响应头、以附件形式打开文件
        response.setHeader("content-disposition", "attachment; fileName=" + fileName);


        FileInputStream fileInputStream = new FileInputStream(filePath + fileName);
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, length);
        }
        fileInputStream.close();
        outputStream.flush();
        outputStream.close();


    }

    @Override
    public FileModel getFileByMD5(String md5) {
        LambdaQueryWrapper<FileModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileModel::getMd5, md5)
                .eq(FileModel::getIsDeleted, 0);
        List<FileModel> list = baseMapper.selectList(queryWrapper);
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public FileModel getFileById(Integer id) {
        return baseMapper.selectById(id);
    }
}




