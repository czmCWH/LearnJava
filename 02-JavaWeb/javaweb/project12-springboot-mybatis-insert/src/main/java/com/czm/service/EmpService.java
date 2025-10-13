package com.czm.service;

import com.czm.entity.Emp;
import org.springframework.stereotype.Service;

/**
 * Service 层，处理业务逻辑
 */

//@Service
public interface EmpService {
    /**
     * 新增员工信息
     * @param emp
     */
    void save(Emp emp);

    /**
     * 新增员工信息 - 模拟 Spring 事务管理，指定事务抛出异常
     * @param emp
     */
    void save2(Emp emp) throws Exception;

    /**
     * 新增员工信息 - 模拟 Spring 事务管理，指定事务行为
     * @param emp
     */
    void save3(Emp emp) throws Exception;
}
