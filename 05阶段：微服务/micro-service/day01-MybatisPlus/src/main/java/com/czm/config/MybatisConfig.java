package com.czm.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 1、初始化核心插件
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 2、添加 MyBatis-Plus 的 分页拦截器 插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));      // 指定数据库方言为 DbType.MYSQL

        return interceptor;
    }

}
