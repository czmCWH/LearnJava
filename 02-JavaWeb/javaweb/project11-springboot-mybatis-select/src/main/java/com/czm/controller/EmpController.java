package com.czm.controller;

import com.czm.entity.Emp;
import com.czm.entity.EmpQueryParam;
import com.czm.entity.PageBean;
import com.czm.entity.Result;
import com.czm.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Controller 层，接收 http 请求，响应数据。
 * 1、接收接口的 page、pageSize 参数；
 * 2、调用 Service 进行分页查询员工信息，获取 PageBean 对象；
 * 3、响应结果
 */
@Slf4j  // 日志打印调试，lombook 框架内置了 Slf4j，所以可以直接使用 Logback 框架输出日志信息
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    // ----------------- 1、分页查询
    /**
     * 分页查询员工信息 --- 原始分页方式
     * @param page 页码
     * @param pageSize 每页条数
     * @return
     */
    @GetMapping("/emps")
    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("--- 开始分页查询：page = {}, pageSize = {}", page, pageSize);
        PageBean<Emp> data = empService.page(page, pageSize);
        return  Result.success(data);
    }

    /**
     * 使用 PageHelper 插件简化分页查询员工信息
     */
    @GetMapping("/emps1")
    public Result page1(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("--- 开始分页查询：page = {}, pageSize = {}", page, pageSize);
        PageBean data = empService.page(page, pageSize);
        return  Result.success(data);
    }

    // ----------------- 2、按条件分页查询
    /**
     * Controller 中接收多个参数，并设置默认值。
     * @DateTimeFormat 是 Spring 的注解注解，用于指定前端传递的时间参数的格式
     */
    @GetMapping("/empsOptn")
    public Result pageOptn(String name,
                          Integer gender,
                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                          @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("--- 条件分页查询：{}，{}，{}, {}，{}，{}", name, gender, begin, end, page, pageSize);
        return Result.success();

    }

    /**
     * url查询参数接收优化
     * 如果controller方法的参数较多，且未来可能继续增加，这会使得方法签名变得复杂难以维护，此时可以考虑将多个请求参数封装为一个对象。
     * 注意：参数实体类中的属性名 需与 请求参数保持一致。
     */
    @GetMapping("/empsOpt")
    public Result pageOpt(EmpQueryParam param) {
        String name = param.getName();
        Integer gender = param.getGender();
        LocalDate begin = param.getBegin();
        LocalDate end = param.getEnd();
        Integer page = param.getPage();
        Integer pageSize = param.getPageSize();

        log.info("--- 条件分页查询-请求参数封装：{}，{}，{}, {}，{}，{}", name, gender, begin, end, page, pageSize);
        PageBean pageBean = empService.page(param);
        return Result.success(pageBean);
    }
}
