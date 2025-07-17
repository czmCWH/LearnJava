package com.czm.mapper;

import com.czm.pojo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 新增用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 根据ID删除用户
     * @param id
     */
    void deleteUser(Long id);

    /**
     * 根据ID更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    User queryUserById(@Param("id") Long id);

    /**
     * 根据ID批量查询用户
     * @param id
     * @return
     */
    List<User> queryUserByIds(@Param("ids") List<Long> ids);

}
