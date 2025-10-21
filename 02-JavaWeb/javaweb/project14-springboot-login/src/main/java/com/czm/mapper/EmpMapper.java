package com.czm.mapper;

import com.czm.entity.Emp;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    /**
     * 根据用户名和密码查询员工 --- 即登录功能
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp selectUsernameAndPassword(Emp emp);

    /**
     * 统计员工职位人数
     * @return Map 中是 职位名称:员工数 的键值对，对应着查询的每一条记录
     */
    @MapKey("pos")  // 由于此处使用 Map 接收，而不是一个实体类。因此需要使用 @MapKey注解，指定查询记录中那个字段的值作为key。
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     */
    @MapKey("name") // 由于此处使用 Map 接收，而不是一个实体类。因此需要使用 @MapKey注解，指定查询记录中那个字段的值作为key。
    List<Map<String, Object>> countEmpGenderData();
}
