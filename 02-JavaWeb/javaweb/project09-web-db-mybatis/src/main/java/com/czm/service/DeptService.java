package com.czm.service;

import com.czm.pojo.Dept;

import java.util.List;

/**
 * 面向接口编程，先写一个接口，根据业务多个实现类。
 * 增强程序的扩展性。
 */

public interface DeptService {
    /**
     * 查询部门列表
     * @return
     */
    List<Dept> list();

    /**
     * 删除部门列表
     * @param id
     */
    void delete(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    void addDept(Dept dept);

    /**
     * 根据ID查询部门
     * @param id
     * @return
     */
    Dept getById(Integer id);

    /**
     * 修改部门信息
     * @param dept
     */
    void update(Dept dept);

    /**
     * 修改部分部门信息
     * @param dept
     */
    void updateOption(Dept dept);

}
