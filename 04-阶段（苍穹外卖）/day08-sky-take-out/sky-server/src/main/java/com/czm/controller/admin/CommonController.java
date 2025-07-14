package com.czm.controller.admin;

import com.czm.result.Result;
import com.czm.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin/common")
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    // 使用阿里云OOS 上传文件
    @PostMapping("/upload")
    public Result<String> updloat(MultipartFile file) {
        // 1、创建上传文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = UUID.randomUUID().toString() + suffix;
        // 2、调用工具类上传文件
        try {
            String url = aliOssUtil.upload(file.getBytes(), objectName);
            // 3、返回图片路径结果
            return Result.success(url);
        } catch (Exception e) {
            log.info("--- 文件上传失败: {}", e.getMessage());
            return Result.error(e.getMessage());
        }

        // 上传成功：https://zm-take-out.oss-cn-hangzhou.aliyuncs.com/4cda22c5-208d-4b00-b070-4e305239b9c1.jpeg
    }
}
