package com.czm.service.impl;

import com.czm.entity.Emp;
import com.czm.entity.EmpExpr;
import com.czm.mapper.EmpExprMapper;
import com.czm.mapper.EmpMapper;
import com.czm.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
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

    @Override
    public Emp getById(Integer id) {
        // 1、调用 mapper 查询员工信息，需要查询 emp、emp_expr 2张表，需要做连表查询
        return empMapper.getById(id);
    }

    @Override
    public Emp getById2(Integer id) {
        // 1、查询员工基本信息封装到 emp 对象中
        Emp emp = empMapper.getById2(id);

        // 2、查询员工工作经历信息封装到 emp 对象中
        List<EmpExpr> exprList = empExprMapper.getByEmpId(id);

        emp.setExprList(exprList);
        // 3、返回 emp 对象
        return emp;
    }

    @Transactional  // 由于操作2张表，所以要开启事务
    public void update(Emp emp) {

        // 1、修改员工基本信息，操作 emp 表
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);

        // 2、修改员工工作经历，操作 emp_expr 表
        // 由于这个操作有3种（增、删、改），采用覆盖的方式来简化操作：即先删后增
        empExprMapper.deleteByEmpId(emp.getId());

        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            // 把工作经历关联员工ID
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
        }
        empExprMapper.insertBatch(exprList);

    }
}
