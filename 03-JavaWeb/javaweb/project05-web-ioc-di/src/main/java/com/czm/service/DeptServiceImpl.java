package com.czm.service;

import com.czm.dao.DeptDao;
import com.czm.dao.DeptDaoImpl;
import com.czm.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 业务逻辑处理层
 */

//@Component      // Spring IOC，程序启动时，会自动创建该类对象，并交由IOC容器管理。
@Service    // 用来标识当前类属于逻辑处理类，也是将该类型交给 IOC 容器管理。与 @Component 作用一样。
public class DeptServiceImpl implements DeptService {

//    private DeptDao deptDao = new DeptDaoImpl();
    @Autowired      // 从 Spring IOC 容器中，自动寻找 Bean 对象，为改变了赋值 --- 依赖注入，ID的实现
    private DeptDao deptDao;

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
