package com.czm.mapper;

import com.czm.entity.Employee;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 新增员工，基于注解方法书写 sql。
     * @param employee
     * @return
     */
    @Insert("insert into employee values (null, #{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{status},#{createTime},#{updateTime}," +
            "#{createUser},#{updateUser})")
    void insert(Employee employee);

    /**
     * 此处不能使用 注解sql查询，只能使用 xml方式。因为前端 name 可能不传，是动态参数。
     * @param name
     * @return
     */
    Page<Employee> list(String name);
}
