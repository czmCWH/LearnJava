package com.czm.controller;

import com.czm.entity.Dept;
import com.czm.entity.Result;
import com.czm.service.DeptService;
import jakarta.annotation.Resource;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 请求处理类
 */

@RestController     // @RestController 底层封装了 @Controller+@ResponseBody
public class DeptController {

    /**
     * 部门列表查询
     * @return 部门列表数据
     */
    @RequestMapping("/depts")
    public List<Dept> getAllDept() {
        // 1、加载并读取 dept.txt 文件
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
    }

//    private DeptService deptService = new DeptServiceImpl();
//    private DeptService deptService = new DeptServiceImpl2();     // 存在强耦合

//    @Autowired      // 应用程序运行时，会自动从 Spring 容器中查找该类型的 Bean 对象，并赋值该成员变量 --- 依赖注入，ID的实现
    @Resource(name = "deptServiceImpl2")    // ⚠️：IOC 中的实体类名默认是 首字母小写的。所以此处不能写 DeptServiceImpl2
    private DeptService deptService;

    @GetMapping("/depts3")      // 限定接口的请求方式，只能以 GET 方式请求
    public Result getAllDept3() {
        // 1、调用 Service，获取数据
        List<Dept> depts = deptService.list();

        // 2、响应数据 JSON 格式
        return Result.success(depts);
    }
}
