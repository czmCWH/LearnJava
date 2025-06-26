package com.czm.service.impl;

import com.czm.entity.EmpLog;
import com.czm.mapper.EmpLogMapper;
import com.czm.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

//    @Transactional(propagation = Propagation.REQUIRED)    // 如果被调用的方法存在事务，则加入该事务，否则创新新事物。
    @Transactional(propagation = Propagation.REQUIRES_NEW)  // 开启一个新事务，不会被调用方法中的事务影响。
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
