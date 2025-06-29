package com.czm.service.impl;

import com.czm.entity.Emp;
import com.czm.entity.EmpLoginInfo;
import com.czm.mapper.EmpMapper;
import com.czm.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public EmpLoginInfo login(Emp emp) {
        // 1、调用 mapper 查询 username、password 是否正确
        Emp empDB = empMapper.selectUsernameAndPassword(emp);

        // 2、判断用户账号密码是否正确，即查询的数据是否为空
        if (empDB != null) {
            // 3、如果查询到数据则构造 EmpLoginInfo 对象返回
            EmpLoginInfo info = new EmpLoginInfo(empDB.getId(), empDB.getUsername(), empDB.getName(), null);
            return info;
        }
        // 4、未查询到返回 null，代表用户名或密码错误
        return null;
    }
}
