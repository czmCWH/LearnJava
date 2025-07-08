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
}
