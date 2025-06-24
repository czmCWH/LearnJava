package com.czm.mapper;

import com.czm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// @Mapper 是 MyBatis 中的注解；作用：程序启动时，会自动生成该接口的代理对象，交由 IOC 容器管理，成为 bean对象。
@Mapper
public interface UserMapper {

    // 在数据库中查询所有用户信息 -- 使用 @Select(sql语句的方式) 称为：基于注解开发
    @Select("select * from user")
    public List<User> list();

    // 基于 Mybatis 的 XML配置文件 开发
    public List<User> findList();
}
