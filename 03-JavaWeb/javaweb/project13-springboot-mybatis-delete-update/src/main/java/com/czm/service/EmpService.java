package com.czm.service;

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
}
