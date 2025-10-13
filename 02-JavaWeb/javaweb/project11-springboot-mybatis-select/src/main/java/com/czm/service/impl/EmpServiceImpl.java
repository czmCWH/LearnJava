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
    EmpMapper empMapper;

    //------------- 1、普通分页查询

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        // 1、调用 mapper 获取总条数
        Long total = empMapper.count();

        // 2、调用 mapper 获取分页列表数
        Integer start = (page - 1) * pageSize;  // 起始索引
        List<Emp> rows = empMapper.page(start, pageSize);
        // 3、封装数据返回

        return new PageBean(total, rows);
    }


    @Override
    public PageBean spPage(Integer page, Integer pageSize) {
        // 1、设置 PageHelper 的分页参数
        PageHelper.startPage(page, pageSize);

        // 2、调用mapper 中的查询方法
        List<Emp> empList = empMapper.spPage();
        Page p = (Page) empList;    // 强转，Page 继承自 ArrayList

        // ⚠️：如果有其它分页sql，需要再写一次  PageHelper.startPage(page, pageSize);，再写对应的sql。

        // 3、封装返回对象
        return  new PageBean(p.getTotal(), p.getResult());
    }


    //------------- 2、按条件实现分页查询
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
