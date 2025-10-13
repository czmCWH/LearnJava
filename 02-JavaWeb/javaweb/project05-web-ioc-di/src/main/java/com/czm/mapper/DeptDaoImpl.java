package com.czm.mapper;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

/**
 * 数据访问层，处理数据访问数据
 */

//@Component      // Spring IOC，程序启动时，会自动创建该类对象，并交由IOC容器管理。也称为 Bean 对象。
@Repository     // 用来标识该类是一个数据访问层类，效果等价于 @Component ，后续基本上不用，因为有 mybatis。
public class DeptDaoImpl implements DeptDao {
    public List<String> list() {
        // 1、加载并读取 dept.txt 文件
        // resources目录下的本地文件会被编译到 Classes 类目录下，通过类加载器可以获取该路径下的所有资源
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("dept.txt");

        List<String> strings = IOUtils.readLines(input, "UTF-8");

        return strings;
    }
}
