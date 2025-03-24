package com.czm.service;

import com.czm.dao.DeptDao;
import com.czm.dao.DeptDaoImpl;
import com.czm.entity.Dept;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 业务逻辑处理层
 */
public class DeptServiceImpl implements DeptService {

    private DeptDao deptDao = new DeptDaoImpl();

    public List<Dept> list() {
        // 1、获取原始数据
        List<String> stringList = deptDao.list();


        // 2、处理数据 - 将数据封装成 List<Dept>

        // 2、解析文本中的数据，并将其封装成集合
        List<Dept> depts = stringList.stream().map((str) -> {
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
