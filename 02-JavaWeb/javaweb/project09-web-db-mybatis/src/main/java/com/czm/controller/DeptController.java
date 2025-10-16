package com.czm.controller;

import com.czm.pojo.Dept;
import com.czm.pojo.Result;
import com.czm.service.DeptService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * controller，控制层，接收请求，调用 Service 层来响应结果。
 * ⚠️ 一般都是先写 controller 的逻辑。
 */

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * Get 请求，获取所有数据
     */
    @GetMapping("/depts")   // ⚠️ 等价于：@RequestMapping(value = "/depts", method = RequestMethod.GET)
    public Result getAllDept() {
        // 1、调用 Service，获取数据
        List<Dept> depts = deptService.list();

        // 2、响应数据 JSON 格式
        return Result.success(depts);
    }

    /**
     * delete 请求，如：/depts?id=1
     */
    @DeleteMapping("/depts")
    public Result delete(Integer id) {
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @PostMapping("/depts")
    public Result addDept(@RequestBody Dept dept) {
        System.out.println("--- post 请求参数 = " + dept);
        deptService.addDept(dept);
        return Result.success();
    }


    // /depts?id=1，id 为 URL查询参数
    // /depts/{id}/{name}，id、name 为 URL路径参数

    /**
     * 根据ID查询部门信息
     * @PathVariable 注解用于接收路径参数。
     */
    @GetMapping("/depts/{id}")
    public Result getById(@PathVariable("id") Integer deptId) {
        System.out.println("---接收到 路径参数 id = " + deptId);
        // 调用 Service 的方法
        Dept dept = deptService.getById(deptId);
        return Result.success(dept);
    }

    /**
     * put 请求更新部门信息
     */
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        System.out.println("接收到请求参数 dept = " + dept);
        deptService.update(dept);
        return Result.success();
    }

    /**
     * put 请求更新部分部门信息 --- 使用 动态sql 以 XML 方式实现
     */
    @PutMapping("/depts2")
    public Result updateOption(@RequestBody Dept dept) {
        System.out.println("接收到请求参数 dept = " + dept);
        deptService.updateOption(dept);
        return Result.success();
    }
}
