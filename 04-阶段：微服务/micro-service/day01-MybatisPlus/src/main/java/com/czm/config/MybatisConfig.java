package com.czm.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 声明配置类，注册 MyBatis-Plus 的核心插件
 */
@Configuration
public class MybatisConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 1、初始化核心插件
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 2、添加 MyBatis-Plus 的 分页拦截器 插件
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor(DbType.MYSQL); // 指定数据库方言为 DbType.MYSQL
        pageInterceptor.setMaxLimit(1000L); // 设置分页上限，每次最多查询1000条
        interceptor.addInnerInterceptor(pageInterceptor);
        // 还可以继续添加 MP 的其它插件
        return interceptor;
    }
}
