package com.czm.service.impl;

import com.czm.dao.DeptDao;
import com.czm.entity.Dept;
import com.czm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 业务逻辑处理层
 */

//@Component      // 将当前类交给 IOC 容器管理 --- 控制反转的实现，IOC
@Service    // 用来标识当前类属于逻辑处理类，也是将该类型交给 IOC 容器管理。与 @Component 作用一样。
public class DeptServiceImpl implements DeptService {

//    private DeptDao deptDao = new DeptDaoImpl();


    // @Autowired 依赖注入，方式1，基于属性注入
    @Autowired      // @Autowired 注解作用，应用程序运行时，会自动从 Spring 容器中查找该类型的 Bean 对象，并赋值该成员变量 --- 依赖注入，ID的实现
    private DeptDao deptDao;

    // @Autowired 依赖注入，方式2，基于构造器注入
//    private final DeptDao deptDao;
//    @Autowired    // 如果当前类的构造函数只有一个，可以省略 @Autowired
//    public DeptServiceImpl(DeptDao deptDao) {
//        this.deptDao = deptDao;
//    }

    // @Autowired 依赖注入，方式3，基于setter方法注入
//    private DeptDao deptDao;
//    @Autowired
//    public void setDeptDao(DeptDao deptDao) {
//        this.deptDao = deptDao;
//    }

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
