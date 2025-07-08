package com.czm.service;

import com.czm.dto.EmployeeDTO;
import com.czm.dto.EmployeeLoginDTO;
import com.czm.dto.EmployeePageQueryDTO;
import com.czm.entity.Employee;
import com.czm.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /***
     * 新增员工
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 员工分页查询
     * @param dto
     * @return
     */
    PageResult page(EmployeePageQueryDTO dto);

    /**
     * 启用禁用员工账号
     * @param status
     * @param id
     */
    void enableOrDisable(Integer status, Long id);

    /**
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    Employee getById(Long id);

    /**
     * 更新员工信息
     * @param dto
     */
    void update(EmployeeDTO dto);
}
