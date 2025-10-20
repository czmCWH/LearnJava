package com.czm.controller;

import com.czm.entity.Emp;
import com.czm.entity.Result;
import com.czm.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Controller：控制层，接受请求、响应数据。
 * 1、批量删除员工；
 * 2、修改员工；
 * 3、异常处理；
 */
@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    // Controller 中接收 /emps?ids=1,2,3 请求参数的2种方式

    /**
     * 方式1，直接通过数组接收
     */
    @DeleteMapping("/emps1")
    Result delete1(Integer[] ids) {  // 通过数组接收前端传递的数组
        log.info("--- 根据ID批量删除 ids = {}", Arrays.toString(ids));
        return Result.success();
    }

    /**
     * 方式2，通过集合方式接收，需要 @RequestParam 注解标识 ids 接收的是url后面的查询参数
     */
    @DeleteMapping("/emps")
    Result delete(@RequestParam List<Integer> ids) {
        log.info("--- 根据ID批量删除 ids = {}", ids);

        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根据ID查询用户信息（基本信息+工作经历）
     * @param id 用户ID
     */
    @GetMapping("/emps/{id}")
    Result getById(@PathVariable Integer id) {  // ⚠️ @PathVariable 注解来接收 URL路径参数
        log.info("--- 回显查询员工信息 id = {}", id);
        Emp emp = empService.getById(id);
//        Emp emp = empService.getById2(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     */
    @PutMapping("/emps")
    Result update(@RequestBody Emp emp) {
        log.info("--- 修改员工基本信息 emp = {}", emp);
        empService.update(emp);
        return Result.success();
    }
}
