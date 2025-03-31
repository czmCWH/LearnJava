package com.czm.mapper;

import com.czm.entity.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Mapper 层，执行SQL语句操作数据库
 */

@Mapper
public interface DeptMapper {
    /**
     * 查询部门列表
     */
//    @Results({
////            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
//    @Select("select * from dept")
    @Select("select id, name, update_time updateTime from dept")
    List<Dept> list();

    /**
     * 根据ID删除部门
     * @param id
     * @return
     */
    @Delete("delete from dept where id = #{id}")
//    void delete(Integer id);
    Integer delete(Integer id);

    /**
     * 新增部门
     */
    @Insert("insert into dept(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    /**
     * 根据ID查询部门
     * @param id
     * @return
     */
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("select * from dept where id = #{id}")
    Dept getById(Integer id);

    /**
     * 更新部门信息
     * @param dept
     */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);

    /**
     * 更新部分部门信息，通过 Mybatis 实现动态SQL。
     * @param dept
     */
    void updateOption(Dept dept);
}
