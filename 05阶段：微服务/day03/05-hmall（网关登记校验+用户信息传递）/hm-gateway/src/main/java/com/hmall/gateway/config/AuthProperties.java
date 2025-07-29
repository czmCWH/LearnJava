package com.hmall.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * AuthProperties 用于加载 application.yaml 文件中 hm.auth 下面的所有属性。
 */

@Data
@Component
@ConfigurationProperties(prefix = "hm.auth")
public class AuthProperties {
    /// 需要被 JWT 校验的路径
    private List<String> includePaths;
    /// 被 JWT 排除校验的路径
    private List<String> excludePaths;
}
