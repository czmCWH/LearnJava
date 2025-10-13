package com.czm.mapper;

import com.czm.entity.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface EmpMapper {
    /**
     * 1、新增员工基本信息（基于 注解方式）
     * @param emp，用 emp 对象的属性名作为 sql 语句中的 #{占位符}
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")   // ⚠️⚠️⚠️ @Options 注解，表示执行 sql 完毕后，把主键 id 取出来赋值给 emp.id
    @Insert("insert into emp values (null,#{username},#{password},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId}," +
            "#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 2、新增员工基本信息（基于 XML方式）
     */
    void insertXml(Emp emp);

}
