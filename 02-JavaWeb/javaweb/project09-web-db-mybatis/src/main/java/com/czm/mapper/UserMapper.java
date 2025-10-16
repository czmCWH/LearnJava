package com.czm.mapper;

import com.czm.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// @Mapper 注解是 MyBatis 提供的。
// @Mapper 作用：程序运行时，会自动为该接口创建一个实现类对象（即动态代理对象），并自动将该实现类对象交由 IOC 容器管理，成为 bean对象。
@Mapper
public interface UserMapper {

    /**
     * 查询数据库中所有用户信息
     *  声明返回值类型，用于存储查询结果。
     * 使用 @Select(sql语句的方式) 称为：基于注解开发
     */
    @Select("select * from user")
    public List<User> list();

    // 基于 Mybatis 的 XML配置文件 开发
    public List<User> findList();
}
