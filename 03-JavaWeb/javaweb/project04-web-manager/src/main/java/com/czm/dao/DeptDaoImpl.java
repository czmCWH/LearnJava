package com.czm.dao;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.List;

/**
 * 数据访问层，处理数据访问数据
 */
public class DeptDaoImpl implements DeptDao {
    public List<String> list() {
        // 1、加载并读取 dept.txt 文件
        // resources目录下的本地文件会被编译到 Classes 类目录下，通过类加载器可以获取该路径下的所有资源
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("dept.txt");

        List<String> strings = IOUtils.readLines(input, "UTF-8");

        return strings;
    }
}
