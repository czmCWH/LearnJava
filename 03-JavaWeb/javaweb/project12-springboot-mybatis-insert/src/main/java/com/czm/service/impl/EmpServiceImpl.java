package com.czm.service.impl;

import com.czm.entity.Emp;
import com.czm.entity.EmpExpr;
import com.czm.entity.EmpLog;
import com.czm.mapper.EmpExprMapper;
import com.czm.mapper.EmpMapper;
import com.czm.service.EmpLogService;
import com.czm.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    // --- 1、实现员工信息新增

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

    // ---- 2、指定 Spring 事务处理异常，优化员工信息新增

    @Transactional(rollbackFor = Exception.class)    // ⚠️⚠️⚠️ 开启 Spring 事务，并指定其能处理所有异常
    @Override
    public void save2(Emp emp) throws Exception {
        // 1、调用 mapper 保存员工的基本信息到 emp 表
        // 补充缺失的字段
        emp.setPassword("123456");
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        // 调用 mapper
//        empMapper.insert(emp);      // 使用 Mybatis 基于Sql注解
        empMapper.insertXml(emp);     // 使用 Mybatis 基于 XML

        Integer id = emp.getId();
        log.info("--- 新增员工的 id = {}", id);
        log.info("--- 模拟事务管理，虽然 emp 表插入成功，但 1/0 抛出异常错误，插入的数据会回滚。");

        // 模拟 运行时异常，执行事务回滚
//        int i = 1/0;  // 模拟抛出 RuntimeException
        if (true) { // 模拟抛出 Exception 异常
            throw new Exception("模拟 Exception 异常");
        }

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


    // ---- 3、模拟 Spring 事务的行为

    @Autowired
    private EmpLogService empLogService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save3(Emp emp) throws Exception {
        try {
            // 1、调用 mapper 保存员工的基本信息到 emp 表
            // 补充缺失的字段
            emp.setPassword("123456");
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            // 调用 mapper
            empMapper.insert(emp);      // 使用 Mybatis 基于Sql注解

            Integer id = emp.getId();

            // 模拟 运行时异常，执行事务回滚
//        int i = 1/0;  // 模拟抛出 RuntimeException
            if (true) { // 模拟抛出 Exception 异常
                throw new Exception("模拟 Exception 异常");
            }

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
        } finally {
            EmpLog empLog = new EmpLog();
            empLog.setOperateTime(LocalDateTime.now());
            empLog.setInfo("插入员工信息 ：" + emp);
            empLogService.insertLog(empLog);
        }
    }
}
