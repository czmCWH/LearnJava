package com.czm.service.impl;

import com.czm.entity.Emp;
import com.czm.entity.EmpLoginInfo;
import com.czm.mapper.EmpMapper;
import com.czm.service.EmpService;
import com.czm.utils.JwtUtils;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /**
     * 登录
     */
    @Override
    public EmpLoginInfo login(Emp emp) {
        // 1、调用 mapper，根据查询 username、password 查询员工信息
        Emp empDB = empMapper.selectUsernameAndPassword(emp);

        // 2、判断用户账号密码是否正确，即查询的数据是否为空
        if (empDB != null) {
            // 3、如果查询到数据则构造 EmpLoginInfo 对象返回
            // 自定义 JWT 令牌有效载荷
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", empDB.getId());
            claims.put("username", empDB.getUsername());
            // 生成登录 JWT 令牌返回给客户端，用于其它请求访问
            String jwt = JwtUtils.generateJwt(claims);
            EmpLoginInfo info = new EmpLoginInfo(empDB.getId(), empDB.getUsername(), empDB.getName(), jwt);
            return info;
        }
        // 4、未查询到返回 null，代表用户名或密码错误
        return null;
    }
}
