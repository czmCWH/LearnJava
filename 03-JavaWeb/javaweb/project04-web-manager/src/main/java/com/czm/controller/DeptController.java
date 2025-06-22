package com.czm.controller;

import com.czm.entity.Dept;
import com.czm.entity.Result;
import com.czm.service.DeptService;
import com.czm.service.DeptServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 请求处理类
 * 运行项目后：
 *  访问 http://localhost:8080/depts2，将以统一数据结构响应返回。
 *
 */

@RestController
public class DeptController {

    /**
     * 部门列表查询
     * @return 部门列表数据
     */
    @RequestMapping("/depts")
    public List<Dept> getAllDept() {
        // 1、加载并读取 dept.txt 文件
        // resources/ 目录下的本地文件会被编译到 Classes 类目录下，此路径称为 类路径，通过类加载器可以获取该路径下的所有资源。
        // this.getClass().getClassLoader() 获取类加载器
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("dept.txt");

        // 读取输入流中的内容
        List<String> strings = IOUtils.readLines(input, "UTF-8");

        // 2、解析文本中的数据，并将其封装成集合
        List<Dept> depts = strings.stream().map((str) -> {
            String[] parts = str.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String name = parts[1];
            LocalDateTime updateTime = LocalDateTime.parse(parts[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new Dept(id, name, updateTime);
        }).toList();


        // 3、返回响应数据(json 格式)
        return depts;
        /*
         ⚠️此处返回的是一个java对象，为什么 SpringBoot 能以 json 的形式返回？
            因为 SpringBoot 底层对此方法使用了 @ResponseBody 注解，此注解可以作用在方法上或者类上。
            @ResponseBody 注解的作用：将方法返回值直接响应，如果返回值类型是 实体对象、集合，将会转换为JSON格式来响应。
            说明：@RestController = @Controller + @ResponseBody

         注意：前后端分离的项目中，一般直接在请求处理类上加 @RestController 注解，就无需在方法上加 @ResponseBody 注解了。
         */
    }

    /**
     * 部门列表查询 --- 统一响应结果
     * @return 部门列表数据
     */
    // 如果 @RequestMapping 注解中不指定请求方式，则可以以任何请求方式获取数据，这样不严谨。
//    @RequestMapping(value = "/depts2", method = RequestMethod.GET)
    @GetMapping("/depts2")      // 限定接口的请求方式，只能以 GET 方式请求
    public Result getAllDept2() {
        // 1、加载并读取 dept.txt 文件
        // resources/ 目录下的本地文件会被编译到 Classes 类目录下，此路径称为 类路径，通过类加载器可以获取该路径下的所有资源。
        // this.getClass().getClassLoader() 获取类加载器
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

        // 3、以统一响应数据(json 格式)的方式返回
        return Result.success(depts);
    }

    /*
        采用3层结构拆分！！！
     */

    private DeptService deptService = new DeptServiceImpl();

    @GetMapping("/depts3")      // 限定接口的请求方式，只能以 GET 方式请求
    public Result getAllDept3() {
        // 1、调用 Service，获取数据
        List<Dept> depts = deptService.list();

        // 2、响应数据 JSON 格式
        return Result.success(depts);
    }
}
