package com.czm.controller.admin;

import com.czm.constant.JwtClaimsConstant;
import com.czm.dto.EmployeeDTO;
import com.czm.dto.EmployeeLoginDTO;
import com.czm.dto.EmployeePageQueryDTO;
import com.czm.entity.Employee;
import com.czm.properties.JwtProperties;
import com.czm.result.PageResult;
import com.czm.result.Result;
import com.czm.service.EmployeeService;
import com.czm.utils.JwtUtil;
import com.czm.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("--- 员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }


    /**
     * 新增员工
     * 注意 @RequestBody 是在 springframework.web 包内，如果导入错了无法接受到请求参数！
     * @return
     */
    @PostMapping
    public Result<String> save(@RequestBody EmployeeDTO employeeDTO ) {     // @RequestBody 接收 json 格式请求参数
        log.info("--- EmployeeController 线程ID = {}", Thread.currentThread().getId());
        log.info("--- 新增员工:{}", employeeDTO);

        employeeService.save(employeeDTO);

        return Result.success();
    }

    /**
     * 员工列表分页查询
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(EmployeePageQueryDTO dto) {  // Get 请求接收普通参数，即查询字符串参数（?key1=value1&key2=value2）。
        log.info("--- 员工分页查询: {}", dto);
        PageResult result = employeeService.page(dto);
        return Result.success(result);
    }

    /**
     * 修改员工账号状态：1启用 、0禁用
     * @param status
     * @return
     */
    @PostMapping("/status/{status}")
    public Result<String> enableOrDisable(@PathVariable Integer status, Long id) {   // @PathVariable 注解用于接收路径参数
        log.info("--- 启用禁用员工: status = {}, id = {}", status, id);
        employeeService.enableOrDisable(status, id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Long id) {
        log.info("--- 根据ID查询员工信息 = {}", id);
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

}
