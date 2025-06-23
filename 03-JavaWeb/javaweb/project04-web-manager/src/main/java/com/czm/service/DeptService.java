package com.czm.service;

import com.czm.entity.Dept;

import java.util.List;

/**
 * 2、业务逻辑处理层
 * 面向接口编程，先写一个接口，根据业务多个实现类。
 * 增强程序的扩展性。
 */

public interface DeptService {
    public List<Dept> list();
}
