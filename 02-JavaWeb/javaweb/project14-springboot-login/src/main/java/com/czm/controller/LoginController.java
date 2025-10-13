package com.czm.controller;

import com.czm.entity.Emp;
import com.czm.entity.EmpLoginInfo;
import com.czm.entity.Result;
import com.czm.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    Result login(@RequestBody Emp emp) {
        log.info("--- 登录操作 = ${}", emp);
        EmpLoginInfo info = empService.login(emp);
        if (info == null) {
            return Result.error("用户名或密码错误！");
        }
        return Result.success(info);
    }
}
