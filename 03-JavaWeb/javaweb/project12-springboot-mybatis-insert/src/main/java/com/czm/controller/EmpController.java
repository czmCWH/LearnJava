package com.czm.controller;

import com.czm.entity.Emp;
import com.czm.entity.Result;
import com.czm.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * post 请求，新增员工信息（员工基本信息 + 工作经历信息）
     * @param emp
     * @return
     */
    @PostMapping("/emps")
    Result save(@RequestBody Emp emp) {
        log.info("--- 新增员工信息：{}", emp);
        empService.save(emp);
        return Result.success();
    }
}
