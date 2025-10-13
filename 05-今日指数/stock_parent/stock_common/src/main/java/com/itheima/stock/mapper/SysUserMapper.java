package com.itheima.stock.mapper;

import com.itheima.stock.pojo.entity.SysUser;

/**
* @author chen
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2025-09-03 19:38:04
* @Entity com.itheima.stock.pojo.entity.SysUser
*/
public interface SysUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

}
