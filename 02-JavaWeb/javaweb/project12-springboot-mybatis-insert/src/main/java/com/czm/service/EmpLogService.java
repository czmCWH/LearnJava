package com.czm.service;

import com.czm.entity.EmpLog;

/**
 * 日志记录 Service
 */
public interface EmpLogService {
    /**
     * 插入操作日志
     */
    void insertLog(EmpLog empLog);
}
