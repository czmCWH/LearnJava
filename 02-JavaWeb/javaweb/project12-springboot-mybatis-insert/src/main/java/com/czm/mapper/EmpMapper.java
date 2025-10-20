package com.czm.mapper;

import com.czm.entity.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 员工信息 Mapper
 */
@Mapper
public interface EmpMapper {
    /**
     * 1、新增员工基本信息（基于 注解方式）
     * @param emp，用 emp 对象的属性名作为 sql 语句中的 #{占位符}

     * Mybatis 的 @Options 注解，用于主键返回，其属性含义如下：
     *      useGeneratedKeys，表示要使用数据库表自动生成主键；
     *      keyProperty，表示获取到的主键要封装到实体类中的那个属性；
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")   // @Options 注解，表示执行 sql 完毕后，把主键 id 取出来赋值给 emp 对象的 id 属性
    @Insert("insert into emp values (null,#{username},#{password},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId}," +
            "#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 2、新增员工基本信息（基于 XML方式）
     */
    void insertXml(Emp emp);

}
