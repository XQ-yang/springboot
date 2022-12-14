package com.yang.springbootaliyunoss.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yang.springbootaliyunoss.entity.Media;
import com.yang.springbootaliyunoss.enums.FileEnum;
import com.yang.springbootaliyunoss.enums.MediaStoreTypeEnum;
import com.yang.springbootaliyunoss.service.MediaService;
import com.yang.springbootaliyunoss.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.yang.springbootaliyunoss.util.Result.*;

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
    public Result<String> mediaUpload(@RequestPart("file") MultipartFile file, @RequestParam Integer storeType, HttpServletRequest request) {
        MediaStoreTypeEnum mediaStoreTypeEnum = MediaStoreTypeEnum.valueOf(storeType);
        return success("上传成功", mediaService.uploadFile(file, mediaStoreTypeEnum, request));
    }

    @ApiOperation("文件列表信息")
    @GetMapping("/mediaList")
    public Result<Page<Media>> fileList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        Page<Media> list = mediaService.mediaListByPage(pageNum, pageSize);
        return success(list);
    }

    @ApiOperation("文件下载")
    @GetMapping("/download")
    public Result<String> mediaDownload(@RequestParam("fileName") String fileName, @RequestParam("type") String type, @RequestParam("storeType") String storeType, HttpServletResponse response) {
        // 获取文件存储方式
        MediaStoreTypeEnum mediaStoreTypeEnum = MediaStoreTypeEnum.codeOf(storeType);

        // 获取文件的分类路径
        String typePath = FileEnum.typePathOf(type).getTypePath();

        try {
            return msg(mediaService.download(fileName, typePath, mediaStoreTypeEnum, response));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("逻辑删除文件记录")
    @GetMapping("/delete/{id}")
    public Result<Boolean> deleteFile(@PathVariable Integer id) {
        return success(mediaService.removeById(id));
    }

}
