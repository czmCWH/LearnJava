package com.czm.mapper;

import com.czm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper // 是 MyBatis 中的注解，表示程序启动时，会自动生成该接口的代理对象，交由IOC容器管理，成为 bean对象。
public interface UserMapper {

    // 在数据库中查询所有用户信息
    @Select("select * from user")
    public List<User> list();
}
