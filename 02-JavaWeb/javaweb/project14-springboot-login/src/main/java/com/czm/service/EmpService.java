package com.czm.service;

import com.czm.entity.Emp;
import com.czm.entity.EmpLoginInfo;

public interface EmpService {
    /**
     * 登录
     */
    EmpLoginInfo login(Emp emp);
}
