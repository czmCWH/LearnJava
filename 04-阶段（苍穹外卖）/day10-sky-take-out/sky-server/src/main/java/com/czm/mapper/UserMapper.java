package com.czm.mapper;

import com.czm.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * C 端 用户模块
 */

@Mapper
public interface UserMapper {

    /**
     * 查询用户的 微信小程序 openid
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User selectByOpenid(String openid);

    /**
     * 插入用户数据，即注册用户
     * @param user
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")   // ⚠️：需返回用户ID给前端
    @Insert("insert into user (openid, name, create_time) values (#{openid}, #{name}, #{createTime})")
    void insert(User user);

    /**
     * 根据用户ID查询用户信息
     */
    @Select("select * from user where id = #{id}")
    User selectById(Long id);
}
