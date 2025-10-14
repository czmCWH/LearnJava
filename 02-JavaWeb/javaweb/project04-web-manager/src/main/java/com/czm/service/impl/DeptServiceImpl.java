package com.czm.service.impl;

import com.czm.dao.DeptDao;
import com.czm.dao.impl.DeptDaoImpl;
import com.czm.entity.Dept;
import com.czm.service.DeptService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 业务逻辑处理层实现类
 */
public class DeptServiceImpl implements DeptService {

    // DeptDao 多态的方式采用不同的实现类
    private DeptDao deptDao = new DeptDaoImpl();

    public List<Dept> list() {
        // 1、调用 Dao 获取原始数据
        List<String> lines = deptDao.list();

        // 2、处理数据 - 将数据封装成 List<Dept>
        // 解析文本中的数据，并将其封装成集合
        List<Dept> depts = lines.stream().map((str) -> {
            String[] parts = str.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String name = parts[1];
            LocalDateTime updateTime = LocalDateTime.parse(parts[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new Dept(id, name, updateTime);
        }).toList();

        // 3、返回封装好的数据
        return depts;
    }
}
