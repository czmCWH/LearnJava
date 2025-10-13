package com.czm.controller;

import com.czm.entity.EmpQueryParam;
import com.czm.entity.PageBean;
import com.czm.entity.Result;
import com.czm.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Controller 层，接收 http 请求，响应数据。
 * 1、接收接口的 page、pageSize 参数；
 * 2、调用 Service 进行分页查询员工信息，获取 PageBean 对象；
 * 3、响应结果
 */

@Slf4j  // 日志打印调试，lombook 框架内置了 Slf4j，所以可以直接使用。
@RestController
public class EmpController {

    @Autowired
    // @Resource(name = "empServiceImpl") // ⚠️：IOC 中的实体类名默认是 首字母小写的。所以此处不能写 DeptServiceImpl2
    private EmpService empService;

    // ----------------- 1、分页查询

    /**
     * 分页查询员工信息
     * @param page 页码
     * @param pageSize 每页条数
     * @return
     */
    @GetMapping("/emps")
    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("--- 开始分页查询：page = {}, pageSize = {}", page, pageSize);
        PageBean data = empService.page(page, pageSize);
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

    /*
     * Controller 层中的方法如果参数较少，可以直接接收参数；如果参数太多，则不便于维护和管理
     * 可以通过实体类对象封装多个参数，注意：实体类中的属性名需与请求参数保持一致。
     */

    /**
     * Controller 中接收多个参数，并设置默认值。
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
