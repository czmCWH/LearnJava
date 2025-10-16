package com.czm.mapper;

import com.czm.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Mapper 层，执行SQL语句操作数据库
 */

@Mapper
public interface DeptMapper {

    /**
     * 查询部门列表
     * Mybatis 结果数据封装，方式1:
     *      @Results+@Result 注解，用于指定返回数据库中字段名 与 返回实体类的属性名映射，column 是数据库表字段；property 是返回实体类属性名。
     */
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("select * from dept")
//    @Select("select id, name, update_time updateTime from dept")  // Mybatis 结果数据封装，方式2：在 sql 语句中取别名
    List<Dept> list();


    /**
     * 根据ID删除部门
     * #{占位符}：
     *      它是 Mybatis 提供的参数占位符，执行 sql 语句时会把 #{} 替换为 ?，生成预编译Sql语句。防止SQL注入攻击、性能高。
     *      作用：运行时把方法接收的实际参数值 替换SQL语句中的参数占位符。
     * return Integer，用于接收 DML 语句执行完毕的返回值，表示该 DML 语句执行完毕影响的行数，也可以使用 void 不接受。
     */
    @Delete("delete from dept where id = #{id}")
//    void delete(Integer id);
    Integer delete(Integer id);


    /**
     * 新增部门
     * dept(name, create_time, update_time)，dept 是数据库表名称，括号内的是数据库中的字段名。
     * values (#{name}, #{createTime}, #{updateTime})，是方法的 dept 参数的属性名。
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
     * #{} 中的内容为 update 方法的 dept 参数的属性名
     */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);


    /**
     * 更新 部分 部门信息，通过 Mybatis 的 XML 语句实现动态SQL。
     * @param dept
     */
    void updateOption(Dept dept);






}
