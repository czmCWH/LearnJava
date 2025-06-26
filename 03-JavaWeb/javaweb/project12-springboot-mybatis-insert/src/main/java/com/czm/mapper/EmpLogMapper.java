package com.czm.mapper;

import com.czm.entity.EmpLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpLogMapper {

    @Insert("insert into emp_log(operate_time, info) values (#{operateTime}, #{info})")
    void insert(EmpLog empLog);
}
