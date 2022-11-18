package com.yang.springbootupanddown.controller;

import com.yang.springbootupanddown.entity.FileModel;
import com.yang.springbootupanddown.exception.ApiException;
import com.yang.springbootupanddown.service.FileModelService;
import com.yang.springbootupanddown.util.Result;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static com.yang.springbootupanddown.util.Result.success;

/**
 * @author: 小强
 * @date: 2022/11/14 0014
 * @IDE: IntelliJ IDEA
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileModelService fileModelService;

    @Value("${uploadPath}")
    private String uploadPath;

    @GetMapping("")
    public String toUpload() {
        return "upload";
    }

    @ResponseBody
    @ApiImplicitParam(name = "file", paramType = "form", value = "上传文件", dataType = "MultipartFile", required = true)
    @PostMapping("/upload")
    public Result<String> upload(@RequestPart(value = "file") MultipartFile file) throws IOException {
        return success(fileModelService.upload(uploadPath, file));
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


    @GetMapping("/download")
    public void downloadFile(String fileName, HttpServletResponse response) throws IOException {
        fileModelService.download(uploadPath, fileName, response);
    }

    @ResponseBody
    @ApiImplicitParam(name = "id", value = "id主键", paramType = "path", dataType = "Integer", required = true, example = "1")
    @GetMapping("/getFile/{id}")
    public Result<FileModel> getFileById(@PathVariable Integer id) {
        FileModel file = fileModelService.getFileById(id);
        if (file != null) {
            return success(file);
        } else {
            throw new ApiException("304", "没有对象");
        }
    }
}
