package com.czm.mapper;

import com.czm.entity.EmpExpr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    /**
     * 根据员工ID批量删除员工工作经历信息
     * @param empIds ⚠️ 由于 ids 参数时动态的，所以需要使用 Mybatis 基于 XML 方法执行动态 SQL。
     */
//    @Delete("delete from emp_expr where emp_id in (1,2,3)")    // 不能基于注解执行sql
    void deleteBatch(List<Integer> empIds);

    /**
     * 根据员工ID查询员工经历List。统一使用XML方式
     */
//    @Select("select * from emp_expr where emp_id = #{empId}")
    List<EmpExpr> getByEmpId(Integer empId);

    /**
     * 根据员工ID删除 工作经历
     */
    void deleteByEmpId(Integer empId);

    /**
     * 批量插入员工工作经历
     */
    void insertBatch(List<EmpExpr> exprList);
}
