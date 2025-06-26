package com.czm.mapper;

import com.czm.entity.Emp;
import com.czm.entity.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * Mapper 层 执行 `sql` 语句。
 * 用于查询员工信息 List
 */

@Mapper
public interface EmpMapper {
    /**
     * 统计员工总记录数
     * @return
     */
    @Select("select count(*) from emp")
    Long count();

    /**
     * 分页查询每页员工信息list
     * @param start 起始索引
     * @param pageSize 每页条数
     * ⚠️⚠️⚠️  d.name deptName 定义 deptName 别名是为了匹配返回数据 Emp 实体类中的属性。
     */
    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id limit #{start}, #{pageSize}")
    List<Emp> page(Integer start, Integer pageSize);

    /**
     * 使用 PageHelper 插件，简化 Mybatis 分页查询
     */
    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id")
    List<Emp> spPage();

    /**
     * 根据多个条件进行分页查询，使用 XML 方式实现动态 sql
     */
    List<Emp> pageOpt(EmpQueryParam param);

}
