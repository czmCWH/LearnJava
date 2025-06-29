package com.czm.mapper;

import com.czm.entity.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmpMapper {

    /**
     * 根据用户名和密码查询员工 --- 即登录功能
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp selectUsernameAndPassword(Emp emp);
}
