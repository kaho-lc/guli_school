package com.lc.oss.controller;

import com.lc.commomutils.ResultClass;
import com.lc.oss.service.OSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OSSController {

    @Autowired
    private OSSService ossService;

    @PostMapping
    public ResultClass upLoadFile(MultipartFile file) {
        //获取上传的文件:MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return ResultClass.Success().data("url", url);
    }
}