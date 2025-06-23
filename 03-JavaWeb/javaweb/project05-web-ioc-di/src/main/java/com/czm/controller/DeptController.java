package com.czm.controller;

import com.czm.entity.Dept;
import com.czm.entity.Result;
import com.czm.service.DeptService;
import com.czm.service.DeptServiceImpl;
import jakarta.annotation.Resource;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 请求处理类
 */

@RestController     // 等价于 @Controller+@ResponseBody
//@Controller
//@ResponseBody
public class DeptController {

    /**
     * 部门列表查询
     * @return 部门列表数据
     */
    @RequestMapping("/depts")
    public List<Dept> getAllDept() {
        // 1、加载并读取 dept.txt 文件
        // resources目录下的本地文件会被编译到 Classes 类目录下，通过类加载器可以获取该路径下的所有资源
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("dept.txt");

        List<String> strings = IOUtils.readLines(input, "UTF-8");

        // 2、解析文本中的数据，并将其封装成集合
        List<Dept> depts = strings.stream().map((str) -> {
            String[] parts = str.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String name = parts[1];
            LocalDateTime updateTime = LocalDateTime.parse(parts[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new Dept(id, name, updateTime);
        }).toList();


        // 3、响应数据(json 格式)
        return depts;
        /*
         ⚠️此处返回的是一个java对象，为什么 SpringBoot 能以 json 的形式返回？
            因为 SpringBoot 底层对此方法使用了 @ResponseBody 注解
            @ResponseBody 注解的作用：将方法返回值直接响应，如果返回值类型是 实体对象、集合，将会转换为JSON格式影响。
            说明：@RestController = @Controller + @ResponseBody

         注意：前后端分离的项目中，一般直接在请求处理类上加 @RestController 注解，就无需在方法上加 @ResponseBody 注解了。
         */
    }

//    private DeptService deptService = new DeptServiceImpl();
//    private DeptService deptService = new DeptServiceImpl2();     // 存在强耦合

//    @Autowired      // @Autowired 注解的作用是从 Spring IOC 容器中，自动寻找 DeptService 类型的 Bean 对象，并为该变量赋值 --- 即：依赖注入，DI的实现
    @Resource(name = "deptServiceImpl2")
    private DeptService deptService;

    @GetMapping("/depts3")      // 限定接口的请求方式，只能以 GET 方式请求
    public Result getAllDept3() {
        // 1、调用 Service，获取数据
        List<Dept> depts = deptService.list();

        // 2、响应数据 JSON 格式
        return Result.success(depts);
    }
}
