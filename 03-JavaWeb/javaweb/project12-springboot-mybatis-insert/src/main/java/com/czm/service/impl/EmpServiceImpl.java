package com.czm.service.impl;

import com.czm.entity.Emp;
import com.czm.entity.EmpExpr;
import com.czm.mapper.EmpExprMapper;
import com.czm.mapper.EmpMapper;
import com.czm.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public void save(Emp emp) {
        // 1、调用 mapper 保存员工的基本信息到 emp 表
        // 补充缺失的字段
        emp.setPassword("123456");
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        // 调用 mapper
//        empMapper.insert(emp);      // 使用 Mybatis 基于Sql注解
        empMapper.insertXml(emp);     // 使用 Mybatis 基于 XML

        // ⚠️⚠️⚠️ 获取 emp 的主键 ID，用户补充到工作经历对象中，用于建立表关系
        // @Options
        Integer id = emp.getId();
        log.info("--- 新增员工的 id = {}", id);

        // 2、调用 mapper 保存员工工作经历到 emp_expr 表
        List<EmpExpr> exprList = emp.getExprList();
        // 给工作经历对象关联员工ID
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(id);
            });
            // 批量保存工作经历
            empExprMapper.insertBatch(exprList);
        }
    }
}
