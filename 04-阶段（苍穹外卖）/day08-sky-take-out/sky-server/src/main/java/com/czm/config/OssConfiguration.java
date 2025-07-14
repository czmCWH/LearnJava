package com.czm.config;

import com.czm.properties.AliOssProperties;
import com.czm.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * alioss 配置类，用于生成 AliOssUtil 对象，并交由 IOC 容器管理。
 */

@Slf4j
@Configuration
public class OssConfiguration {

    @Bean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties) {
        log.info("--- 初始化阿里云OSS工具类 AliOssUtil");
        AliOssUtil aliOssUtil = new AliOssUtil(aliOssProperties.getEndpoint(), aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(), aliOssProperties.getBucketName());
        return aliOssUtil;
    }
}
