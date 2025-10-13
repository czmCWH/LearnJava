package com.czm.service;

import com.czm.entity.Emp;
import com.czm.entity.EmpLoginInfo;

public interface EmpService {

    EmpLoginInfo login(Emp emp);
}
