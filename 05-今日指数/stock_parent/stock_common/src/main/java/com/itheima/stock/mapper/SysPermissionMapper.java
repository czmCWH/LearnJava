package com.itheima.stock.mapper;

import com.itheima.stock.pojo.entity.SysPermission;

/**
* @author chen
* @description 针对表【sys_permission(权限表（菜单）)】的数据库操作Mapper
* @createDate 2025-09-03 19:38:04
* @Entity com.itheima.stock.pojo.entity.SysPermission
*/
public interface SysPermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

}
