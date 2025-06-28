package com.czm.controller;

import com.czm.entity.Result;
import com.czm.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Controller：控制层，接受请求、响应数据。
 * 功能1、批量删除员工信息；
 */

@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @DeleteMapping("/emps1")
    Result delete1(Integer[] ids) {  // 通过数组接收前端传递的数组
        log.info("--- delete 数组方式接收传递的数组 = {}", Arrays.toString(ids));
        return Result.success();
    }

    // ⚠️：通过集合方式接收前端传递数组时，需要 @RequestParam 注解！
    @DeleteMapping("/emps")
    Result delete(@RequestParam List<Integer> ids) {
        log.info("--- delete 集合方式接收传递的数组 = {}", ids);

        empService.delete(ids);
        return Result.success();
    }
}
