package com.czm.mapper;

import com.czm.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    //查询部门列表
//    @Results({
////            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
//    @Select("select * from dept")
    @Select("select id, name, update_time updateTime from dept")
    public List<Dept> list();
}
