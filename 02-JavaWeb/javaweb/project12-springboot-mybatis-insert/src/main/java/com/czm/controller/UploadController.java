package com.czm.controller;

import com.czm.entity.AliOSSProperties;
import com.czm.entity.Result;
import com.czm.utils.AliyunOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传 Controller
 */
@Slf4j
@RestController
public class UploadController {

    /**
     * 1、文件上传 - 本地存储
     * 接收前端 form 表单提交的文件，存储到 java 本地服务器
     */
    @PostMapping("/upload")
    Result upload(String username, Integer age, MultipartFile file) throws IOException {
        log.info("--- 本地文件上传参数 = {}, {}, {}", username, age, file);

        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();
        String fileTypeName = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 通过 uuid 随机生成字符串作为文件名
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + fileTypeName;

        // 1、把前端上传的文件，转存到本地磁盘
        file.transferTo(new File("/Users/chen/Desktop/" + fileName));

        return Result.success(fileName);
    }



    // --------------- 2、文件上传 - 存储到阿里云OSS

    // 方式1：直接项目硬编码配置常量。缺点：不方便的管理
//    private final String bucketName = "zm-java";    // oss 上的桶空间名
//    private final String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";  // oss 上桶对应的域名

    // 方法2：通过 Spring 注解使用项目 resources/application.xxx 配置文件里的 常量
    @Value("${aliyun.oss.bucket}")
    private String bucketName;
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    // 方式3：通过 @ConfigurationProperties 注解的配置类 获取配置信息
    @Autowired
    private AliOSSProperties aliyOSSProperties;

    @PostMapping("/upload2")
    Result uploadOSS(MultipartFile file) throws Exception {
        log.info("---- czm OSS 配置对象 = {}", aliyOSSProperties.toString());

        // 1、获取文件原始文件名，截取文件后缀
        String originalFilename = file.getOriginalFilename();   // 获取原始文件名
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".")); // 获取文件名后缀

        // 2、调用阿里云OSS工具类，讲文件上传到OSS
        String fileUrl = AliyunOSSUtils.upload(endpoint, bucketName, file.getBytes(), extName);
        System.out.println("--- 文件上传成功，文件访问路径 = " + fileUrl);

        // 3、返回图片路径到前端
        return Result.success(fileUrl);
    }
}
