package com.czm.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

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

}
