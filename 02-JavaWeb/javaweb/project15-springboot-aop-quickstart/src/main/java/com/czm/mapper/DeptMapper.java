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
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })  //  Mybatis数据封装方式1，@Results 设置返回数据库中字段名 与 返回实体类的属性名映射
    @Select("select * from dept")
//    @Select("select id, name, update_time updateTime from dept")  // Mybatis数据封装方式2 取别名
    List<Dept> list();

    /**
     * 根据ID删除部门
     * @param id
     * @return
     */
    @Delete("delete from dept where id = #{id}")    // Mybatis 中的 #{} 表示 预编译 sql 中的占位符。可以用 ${} 直接拼接sql，会造成sql注入
//    void delete(Integer id);
    Integer delete(Integer id);     // // 执行 DML 语句 返回 Integer，表示该 DML 执行影响的记录数。用于判断 sql 执行是否成功。

    /**
     * 新增部门
     * dept(name, create_time, update_time)，dept 是数据库表名称，后面是数据库中的字段名。
     * values (#{name}, #{createTime}, #{updateTime})，中的字段是 insert 方法中 dept 参数的属性。
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
    })  //  Mybatis 封装返回数据，@Results 设置查询结果字段名 与 返回实体对象的属性名映射
    @Select("select * from dept where id = #{id}")
    Dept getById(Integer id);

    /**
     * 更新部门信息
     * @param dept
     * #{} 中的内容为 dept 参数的属性名
     */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);

    /**
     * 更新 部分 部门信息，通过 Mybatis 的 XML 语句实现动态SQL。
     * @param dept
     */
    void updateOption(Dept dept);
}
