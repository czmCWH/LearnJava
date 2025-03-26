package com.czm.service.impl;

import com.czm.entity.Dept;
import com.czm.mapper.DeptMapper;
import com.czm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务逻辑处理层
 */

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    public List<Dept> list() {
        // 1、调用 Mapper 方法，获取数据库中的数据并返回
        return deptMapper.list();
    }
}
