package com.llg.controller;

import com.llg.pojo.Result;
import com.llg.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 李龙
 * @version 1.0
 */
@RestController
@Slf4j
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
//        log.info("文件上传:{},{},{}", username, age, image);
//
//        String originalFilename = image.getOriginalFilename();
//
//        //截取最后一个小数点的后缀（.jpg   .txt）
//        int lastIndex = originalFilename.lastIndexOf(".");
//        String exName = originalFilename.substring(lastIndex);
//        //通过UUID的randomUUID()方法加上后缀来唯一识别该文件
//        String newFileName= UUID.randomUUID().toString()+exName;
//
//        image.transferTo(new File("E:\\chromeDownload\\" + newFileName));
//
//        return Result.success();
//    }
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        //查看文件名
        log.info("文件上传:{}",image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        //查看文件的url
        log.info("文件成功上传,{}",url);
        return Result.success(url);
    }
}
