package com.czm.service;

import com.czm.entity.Emp;
import org.springframework.stereotype.Service;

/**
 * Service 层，处理业务逻辑
 */

@Service
public interface EmpService {
    /**
     * 新增员工信息
     * @param emp
     */
    void save(Emp emp);
}
