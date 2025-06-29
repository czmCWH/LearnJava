package com.czm.mapper;

import com.czm.entity.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper：数据访问层，负责数据访问操作。
 */

@Mapper
public interface EmpMapper {

    /**
     * 批量删除员工信息
     * @param ids ⚠️ 由于 ids 参数时动态的，所以需要使用 Mybatis 基于 XML 方法执行动态 SQL。
     */
//    @Delete("delete from emp where id in (1,2,3)")    // 不能基于注解执行sql
    void deleteBatch(List<Integer> ids);

    /**
     * 根据ID查询 员工基本信息+工作经历列表
     * @param empId
     * @return
     */
//    @Select("select * from emp e left join emp_expr ex on e.id = ex.emp_id where e.id = #{empId};")
    Emp getById(Integer empId);

    /**
     * 根据 empId 直接查询员工所有信息
     */
    @Select("select * from emp where id = #{empId}")
    Emp getById2(Integer empId);

    /**
     * 更新员工信息
     * 由于 员工信息有些只传递部分字段，那么其它字段就为null，避免错误修改，需使用动态sql
     */
    void update(Emp emp);
}
