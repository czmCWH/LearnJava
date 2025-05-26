package com.czm.controller;

import com.czm.entity.Dept;
import com.czm.entity.Result;
import com.czm.service.DeptService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * controller，控制层，接收请求，调用 Service 层来响应结果。
 * 一般都是先写 controller 的逻辑。
 */

// @RequestMapping 作用在类上表示公共路径的抽取，如下缩写的路径就都可以省略为：
//  @GetMapping、@DeleteMapping、@PostMapping、@GetMapping("/{id}")、 @PutMapping
//@RequestMapping("/depts")

@RestController     // 等价于 @Controller+@ResponseBody
public class DeptController {

//    @Autowired
    @Resource(name = "deptServiceImpl")
    private DeptService deptService;

    /**
     * Get 请求，获取所有数据
     * @return
     */
    @GetMapping("/depts")      // 限定接口的请求方式，只能以 GET 方式请求
    public Result getAllDept() {
        // 1、调用 Service，获取数据
        List<Dept> depts = deptService.list();

        // 2、响应数据 JSON 格式
        return Result.success(depts);
    }

    // 如下代码所示发送 delete 请求:
    // /depts?id=1

    // 获取请求参数，方式1,通过原始的 HttpServletRequest 对象获取请求参数
//    @RequestMapping(value = "/depts", method = RequestMethod.DELETE)
//    @DeleteMapping("/depts")    // 简写方式
//    public Result delete(HttpServletRequest request) {
//        String id = request.getParameter("id");
//        int idInt = Integer.parseInt(id);
//        System.out.println("--- 删除ID = " + idInt);
//        return Result.success();
//    }

    // 获取请求参数，方式2（记住），通过 Spring 提供的 `@RequestParam` 注解， 将请求参数绑定给方法形参。
//    @DeleteMapping("/depts")    // 简写方式
//    public Result delete(@RequestParam(value = "id", required = false) Integer deptId) {
//        // @RequestParam 注解 required 属性默认为true，代表 id 参数必须传递，如果不传递将报错：code=400。如果参数可选，可以将 required 属性设置为false。
//        System.out.println("--- 2删除ID = " + deptId);
//        return Result.success();
//    }

    // 获取请求参数，方式3（推荐），如果请求参数名与形参变量名相同，直接定义方法形参即可接收。(省略@RequestParam)
    @DeleteMapping("/depts")    // 简写方式
    public Result delete(Integer id) {
        System.out.println("--- 3删除ID = " + id);
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增部门
     * @param dept
     * @return
     */
    // @RequestBody 注解，用来接收 json 格式请求参数，必须用对应参数的对象接收。
    @PostMapping("/depts")
    public Result addDept(@RequestBody Dept dept) {
        System.out.println("--- post 请求参数 = " + dept);
        deptService.addDept(dept);
        return Result.success();
    }

    // /depts?id=1，id 为普通参数
    // /depts/{id}/{name}，id、name 为路径参数

    /**
     * 根据ID查询部门信息
     * @param id
     * @return
     *
     * @PathVariable 注解用于接收路径参数。
     */
    @GetMapping("/depts/{id}")
    public Result getById(@PathVariable Integer id) {
        System.out.println("---接收到 路径参数 id = " + id);
        // 调用 Service 的方法
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * put 请求更新部门信息
     * @param dept
     * @return
     */
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        System.out.println("接收到请求参数 dept = " + dept);
        deptService.update(dept);
        return Result.success();
    }

    /**
     * put 请求更新部分部门信息
     * @param dept
     * @return
     */
    @PutMapping("/depts2")
    public Result updateOption(@RequestBody Dept dept) {
        System.out.println("接收到请求参数 dept = " + dept);
        deptService.updateOption(dept);
        return Result.success();
    }
}
