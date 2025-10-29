package com.czm.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.czm.pojo.dto.PageDTO;
import com.czm.pojo.entity.User;
import com.czm.pojo.dto.UserFormDTO;
import com.czm.pojo.query.UserQuery;
import com.czm.pojo.vo.UserVO;
import com.czm.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理 --- 使用 MyBatis-Plus 的 Service 接口实现 CRUD 操作数据库表。
 */

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor    // ⚠️⚠️⚠️ 不必写 @Autowired 方式注入，只对 final 类型成员变量Spring注入
public class UserController {

//    @Autowired
//    private IUserService userService;

    private final IUserService userService;

    /**
     * 新增用户
     */
    @PostMapping
    public void saveUser(@RequestBody UserFormDTO userFormDTO) {    // @RequestBody 接收 json 格式参数
        // 使用 hutool 框架 把 UserFormDTO 转换为 User
        User user = BeanUtil.copyProperties(userFormDTO, User.class);   // ⚠️ 此方法有 hutool 框架提供
        userService.save(user);
    }

    /**
     * 根据ID删除用户
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {  // @PathVariable 接收 URL 路径参数
        userService.removeById(id);
    }

    /**
     * 根据ID查询用户
     */
    @GetMapping("/{id}")
    public UserVO getUser(@PathVariable("id") Long userId) {     // @PathVariable 接收 URL 路径参数
        // 1、直接通过 IService 查询，不查询 addresses
//        User user = userService.getById(userId);
//        return BeanUtil.copyProperties(user, UserVO.class);

        // 2：使用 MP 的 Db 工具类查询 --- 返回 addresses
        return userService.queryUserAndAddressById(userId);
    }

    /**
     * 根据ID批量查询用户
     */
    @GetMapping
    public List<UserVO> queryByIds(@RequestParam("ids") List<Long> ids) {   // ⚠️：Get + @RequestParam 注解，接收 form-data 格式参数。
        log.info("--- 根据ID列表批量查询 ids: {}", ids);
        // 1、直接通过 IService 查询，不查询 addresses
//        List<User> users = userService.listByIds(ids);
//        return BeanUtil.copyToList(users, UserVO.class);

        // 2、：通过 Db 工具类查询 --- 包含用户地址
        return userService.queryUserAndAddressByIds(ids);
    }

    /**
     * 根据ID扣减金额
     * 涉及到业务：用户状态是否正常、用户余额是否充足。
     * @param id 用户ID
     * @param amount 扣减的金额
     */
    @PutMapping("/{id}/deduction/{amount}")
    public void updateBalanceById(@PathVariable("id") Long id, @PathVariable("amount") Integer amount) {
        //1、直接通过 IService 接口 的自定义方法处理
//        userService.deductionBalanceById(id, amount);

        // 2、使用 IService 的 Lambda 表达式实现
        userService.deductionBalanceByIdWithLambda(id, amount);
    }

    /**
     * IService的Lambda查询 - 根据复杂条件查询用户列表
     */
    @PostMapping("/list")
    public List<UserVO> queryList(@RequestBody UserQuery userQuery) {
        // 获取查询参数
        String username = userQuery.getName();
        Integer status = userQuery.getStatus();
        Integer maxBalance = userQuery.getMaxBalance();
        Integer minBalance = userQuery.getMinBalance();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

       // --- 方式1：一个一个的写判断逻辑
//       if (StrUtil.isNotBlank(username)) {
//           queryWrapper.lambda().like(User::getUsername, username);
//       }

        // --- 方式2：通过 Lambda Wrapper 简化判断
        /*
         * 参数1：条件判断，如果成立则配置查询条件到 where 中
         * 参数2：查询的值
         * 参数3：查询的字段
         */
//        queryWrapper.lambda()
//                .like(StrUtil.isNotBlank(username), User::getUsername, username)  // 根据 条件判断 决定是否设置 查询条件 到 where 中
//                .eq(status != null, User::getStatus, status)
//                .le(maxBalance != null, User::getBalance, maxBalance)
//                .ge(minBalance != null, User::getBalance, minBalance);
//
//       List<User> users = userService.list(queryWrapper);
//
//       return BeanUtil.copyToList(users, UserVO.class);

        // --- 方式3：直接通过 IService 的 lambdaQuery 查询
        List<User> userList = userService.lambdaQuery()
                .like(StrUtil.isNotBlank(username), User::getUsername, username)
                .eq(status != null, User::getStatus, status)
                .le(maxBalance != null, User::getBalance, maxBalance)
                .ge(minBalance != null, User::getBalance, minBalance)
                .list();
        return BeanUtil.copyToList(userList, UserVO.class);

//        List<UserVO>  list = userService.queryList(userQuery.getName(), userQuery.getStatus(), userQuery.getMinBalance(), userQuery.getMaxBalance());
    }


    /**
     * 更新用户余额 ------- 使用 IService 的 Lambda 更新
     */


    /**
     * Page分页查询 - 根据条件
     */
    @GetMapping("/page")
    public PageDTO<UserVO> queryUsersPage(UserQuery query) {
        return userService.queryUsersPage(query);

    }

}
