package com.czm.service;

import com.czm.dto.EmployeeDTO;
import com.czm.dto.EmployeeLoginDTO;
import com.czm.entity.Employee;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void save(EmployeeDTO employeeDTO);

}
