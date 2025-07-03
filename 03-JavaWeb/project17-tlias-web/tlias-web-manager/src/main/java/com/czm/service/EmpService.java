package com.czm.service;

import com.czm.entity.EmpQueryParam;
import com.czm.entity.PageBean;

import java.time.LocalDate;

/**
 * service 层，处理业务逻辑。
 * 1、调用 Mapper 查询员工信息总条数 total；
 * 2、调用 Mapper 查询员工信息列表；
 * 3、封装 PageBean 对象返回；
 */

public interface EmpService {
    /**
     * 分页查询员工信息
     * @param page 页码
     * @param pageSize 每页条数
     * @return
     */
    PageBean page(Integer page, Integer pageSize);


    /**
     * 分页查询员工信息，使用 PageHelper 简化 Mybatis 分页查询
     */
    PageBean spPage(Integer page, Integer pageSize);

    /**
     * 按条件分页查询（可选方法实现）
     */
    default PageBean page(String name, Integer gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        return null;
    }

    /**
     * 通过实体类封装多个请求参数
     */
    PageBean page(EmpQueryParam param);
}
