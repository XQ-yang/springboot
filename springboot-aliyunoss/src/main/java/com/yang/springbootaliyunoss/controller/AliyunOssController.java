package com.yang.springbootaliyunoss.controller;

import com.yang.springbootaliyunoss.entity.Media;
import com.yang.springbootaliyunoss.service.MediaService;
import com.yang.springbootaliyunoss.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.yang.springbootaliyunoss.util.Result.success;

/**
 * @author: 小强
 * @date: 2022/11/22 0022
 * @IDE: IntelliJ IDEA
 */
@Api(tags = "阿里云OSS")
@Controller
@RequestMapping("/aliyunoss")
public class AliyunOssController {
    @Autowired
    MediaService mediaService;

    @GetMapping("")
    public String toUpload() {
        return "upload";
    }

    @ApiOperation("文件上传")
    @ResponseBody
    @PostMapping("/upload")
    public Result<String> mediaUpload(@RequestPart("file") MultipartFile file) {
        return success("上传成功", mediaService.uploadFile(file));
    }

    @GetMapping("/toDownload")
    public String toDownload(Model model) {
        List<Media> list = mediaService.list();
        System.err.println(list.size());
        model.addAttribute("mediaList", list);

        return "downloadPage";
    }

    @ApiOperation("文件下载")
    @ResponseBody
    @GetMapping("/download")
    public void mediaDownload(@RequestParam("fileName") String fileName, HttpServletResponse response) {
        mediaService.download(fileName, response);
    }

}
