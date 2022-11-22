package com.yang.springbootaliyunoss.controller;

import com.yang.springbootaliyunoss.service.MediaService;
import com.yang.springbootaliyunoss.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.yang.springbootaliyunoss.util.Result.success;

/**
 * @author: 小强
 * @date: 2022/11/22 0022
 * @IDE: IntelliJ IDEA
 */
@Api(tags = "阿里云OSS")
@RestController
@RequestMapping("/aliyunoss")
public class AliyunOssController {
    @Autowired
    MediaService mediaService;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result<String> mediaUpload(@RequestPart("file") MultipartFile file) {
        return success("上传成功", mediaService.uploadFile(file));
    }

}
