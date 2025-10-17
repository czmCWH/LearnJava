package com.czm.service.impl;

import com.czm.entity.Emp;
import com.czm.entity.EmpQueryParam;
import com.czm.entity.PageBean;
import com.czm.mapper.EmpMapper;
import com.czm.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 处理员工信息查询
 */

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /**
     * 1、普通分页查询
     * @param page 页码
     * @param pageSize 每页条数
     */
    @Override
    public PageBean<Emp> page(Integer page, Integer pageSize) {
        // 1、调用 mapper 获取总条数
        Long total = empMapper.count();

        // 2、调用 mapper 获取分页列表数
        // 计算起始索引
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.page(start, pageSize);

        // 3、封装数据返回
        return new PageBean<Emp>(total, rows);
    }

    /**
     * PageHelper 插件实现分页查询
     */
    @Override
    public PageBean spPage(Integer page, Integer pageSize) {
        // 1、设置 PageHelper 的分页参数，PageHelper 会自动完成分页操作
        PageHelper.startPage(page, pageSize);

        // 2、调用mapper 中的查询方法
        List<Emp> empList = empMapper.spPage();

        // 3、解析查询结果
        // PageHelper 插件会将分页查询的结果封装到 Page<T> 分页查询结果对象中，T 表示分页查询结果记录数据的泛型
        Page<Emp> p = (Page<Emp>) empList;   // 强转，Page 继承自 ArrayList

        // ⚠️：如果有其它分页sql，需要再写一次  PageHelper.startPage(page, pageSize);，再写对应的sql。

        // 4、封装返回对象
        return new PageBean<Emp>(p.getTotal(), p.getResult());
    }


    /**
     * 按条件分页查询
     */
    @Override
    public PageBean page(EmpQueryParam param) {
        // 1、设置 PageHelper 的分页参数
        PageHelper.startPage(param.getPage(), param.getPageSize());

        // 2、调用 Mapper 查询方法
        List<Emp> empList = empMapper.pageOpt(param);
        Page p = (Page) empList;

        // 3、封装 PageBean 对象并返回
        return new PageBean(p.getTotal(), p.getResult());
    }
}
