package com.czm.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置类，自动引入配置文件中的值
 */
@Data   // lombok 生成 get/set 方法
@Component  // 交给 IOC容器 管理
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOSSProperties {
    private String endpoint;
    private String bucket;
}
