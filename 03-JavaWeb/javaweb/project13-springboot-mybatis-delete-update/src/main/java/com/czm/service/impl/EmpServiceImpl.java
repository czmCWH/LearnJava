package com.czm.service.impl;

import com.czm.mapper.EmpExprMapper;
import com.czm.mapper.EmpMapper;
import com.czm.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    EmpMapper empMapper;

    @Autowired
    EmpExprMapper empExprMapper;

    @Transactional  // 开启事务，以为此操作涉及多张表的删除操作
    public void delete(List<Integer> ids) {
        // 1、删除 emp 表中员工基本信息
        empMapper.deleteBatch(ids);
        // 2、删除 emp_expr 表中员工的工作经历
        empExprMapper.deleteBatch(ids);
    }
}
