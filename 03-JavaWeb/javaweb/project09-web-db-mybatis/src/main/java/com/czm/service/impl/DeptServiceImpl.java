package com.czm.service.impl;

import com.czm.entity.Dept;
import com.czm.mapper.DeptMapper;
import com.czm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 *  service，业务逻辑处理层
 */

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 查询部门列表
     * @return
     */
    public List<Dept> list() {
        // 1、调用 Mapper 方法，获取数据库中的数据并返回
        return deptMapper.list();
    }

    /**
     * 根据ID删除部门
     * @param id
     */
    @Override
    public void delete(Integer id) {
        // 通过根据 sql 影响的行数来判断是否执行成功，基本上不用
        Integer rows = deptMapper.delete(id);
        System.out.println("影响的行数" + rows);
    }

    /**
     * 新增部门
     * @param dept
     */
    @Override
    public void addDept(Dept dept) {
        // 1、补充基础属性
        dept.setUpdateTime(LocalDateTime.now());
        dept.setCreateTime(LocalDateTime.now());

        // 2、调用 Mapper 的 新增方法
        deptMapper.insert(dept);
    }

    /**
     * 数据回显
     * @param id
     * @return
     */
    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    /**
     * 修改部门信息
     * @param dept
     */
    @Override
    public void update(Dept dept) {
        // 1、补充基础属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 2、调用 mapper
        deptMapper.update(dept);
    }

    @Override
    public void updateOption(Dept dept) {
        // 1、补充基础属性
//        dept.setCreateTime(LocalDateTime.now());
        // 2、调用 mapper
        deptMapper.updateOption(dept);
    }

}
