package com.czm.service;

import com.czm.entity.Emp;

import java.util.List;

/**
 * Service：业务逻辑层，数据封装、逻辑处理等。
 */

public interface EmpService {
    /**
     * 根据 ids 集合删除员工
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 员工信息回显，方式一
     * @param id
     */
    Emp getById(Integer id);

    /**
     * 员工信息回显，方式二
     * @param id
     */
    Emp getById2(Integer id);


    /**
     * 修改员工基本信息
     */
    void update(Emp emp);
}
