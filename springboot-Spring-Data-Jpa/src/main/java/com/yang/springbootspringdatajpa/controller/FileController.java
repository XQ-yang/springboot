package com.yang.springbootspringdatajpa.controller;

import com.yang.springbootspringdatajpa.entity.FileModel;
import com.yang.springbootspringdatajpa.repository.FileRepository;
import com.yang.springbootspringdatajpa.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import static cn.hutool.crypto.SecureUtil.md5;
import static com.yang.springbootspringdatajpa.util.Result.error;
import static com.yang.springbootspringdatajpa.util.Result.success;

/**
 * @author: 小强
 * @date: 2022/11/18 0018
 * @IDE: IntelliJ IDEA
 */
@Api(tags = "文件Controller")
@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileRepository fileRepository;

    @Value("${uploadPath}")
    private String uploadPath;

    @GetMapping("")
    public String toUpload() {
        return "upload";
    }

    @ResponseBody
    @ApiOperation("上传文件")
    @ApiImplicitParam(name = "file", paramType = "form", value = "上传文件", dataType = "MultipartFile", required = true)
    @PostMapping("/upload")
    public Result<String> upload(@RequestPart(value = "file") MultipartFile file) throws IOException {
        if (file == null) {
            return error("没有选择文件");
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

        // 文件md5
        String md5 = md5(file.getInputStream());
        String downloadUrl;

        FileModel fileByMD5 = getFileByMD5(md5);
        if (fileByMD5 != null) {
            downloadUrl = fileByMD5.getDownloadUrl();
        } else {
            file.transferTo(new File(uploadPath + fileName));
            downloadUrl = "http://localhost:8080/jpa/file/download?fileName=" + fileName;
        }

        // 保存到数据库
        FileModel fileModel = new FileModel();
        fileModel.setFileName(originalFilename);
        fileModel.setType(type);
        fileModel.setSize(size);
        fileModel.setMd5(md5);
        fileModel.setNewName(fileName);
        fileModel.setDownloadUrl(downloadUrl);
        fileRepository.save(fileModel);

        return success(downloadUrl);
    }

    @GetMapping("/toDownload")
    public String downloadPage(Model model) {
        File file = new File(uploadPath);
        if (file.exists()) {
            String[] list = file.list();
            model.addAttribute("fileList", list);
        }

        return "downloadPage";
    }

    @ApiOperation(value = "下载文件")
    @GetMapping("/download")
    public void downloadFile(String fileName, HttpServletResponse response) throws IOException {

        // 设置响应编码
        response.setCharacterEncoding("UTF-8");

        // 设置响应头、以附件形式打开文件
        response.setHeader("content-disposition", "attachment; fileName=" + fileName);


        FileInputStream fileInputStream = new FileInputStream(uploadPath + fileName);
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


    public FileModel getFileByMD5(String md5) {
        if (fileRepository.findByMd5(md5) != null) {
            return fileRepository.findByMd5(md5).get(0);
        }
        return null;
    }
}
