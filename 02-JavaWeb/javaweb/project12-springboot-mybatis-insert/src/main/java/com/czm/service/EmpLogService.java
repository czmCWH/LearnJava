package com.czm.service;

import com.czm.entity.EmpLog;

public interface EmpLogService {
    /**
     * 插入操作日志
     */
    void insertLog(EmpLog empLog);
}
