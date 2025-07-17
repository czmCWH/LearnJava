package com.czm.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.czm.pojo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * MyBatis-Plus 核心功能 - 条件构造器
 */

@SpringBootTest
public class WrapperTest {

    @Autowired
    UserPlusMapper userPlusMapper;

    /**
     * 案例1：查询出名字中带 o，存款大于 1000 的用户（id, username, info, balance）
     * select d, username, info, balance from user where username like '%o%' and balance >= 1000
     */
    @Test
    public void testQueryWrapper1() {

        // 1、创建条件构造器
//        QueryWrapper<User> wrapper = new QueryWrapper<User>();
//        // 查询的字段 id, username, info, balance
//        wrapper.select("id","username","info","balance");
//        // 查询条件1：名字带 o
//        wrapper.like("username", "o");
//        // 查询条件2：balance >= 1000
//        wrapper.ge("balance", 1000);

        // 👉👉👉---- 改用  Lambda Wrapper 实现
        // 优化：在 Java 代码中硬编码方式写死了数据库表的字段名，不够灵活优雅，容易出错；
        // 可以代替为：LambdaQueryWrapper 和 LambdaUpdateWrapper

        // 1、创建条件构造器
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        // 设置查询的字段
        wrapper.select(User::getId, User::getUsername, User::getInfo, User::getBalance);

        // 设置查询条件
        wrapper.like(User::getUsername, "o");
        wrapper.ge(User::getBalance, 1000);


        // 2、执行sql查询
        List<User> list = userPlusMapper.selectList(wrapper);

        System.out.println("---- 查询结果：");
        for (User user : list) {
            System.out.println(user);
        }
    }

    /**
     * 案例2：更新用户名为 jack 的用户余额为 2222
     * update user set balance = 2222 where username = 'jack'
     */
    @Test
    public void testQueryWrapperUpdate() {

        User user = new User();
        user.setBalance(2222);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", "jack");
        userPlusMapper.update(user, wrapper);
    }

    /**
     * 案例3：更新 id 为 1,2,4 的用户余额扣 200
     * update user set balance = balance - 200 where id in (1,2,4)
     */
    @Test
    public void testUpdateWrapper2() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        // 1、自定义更新的语句
        // ⚠️：这样不合理，因为在 Service 层直接操作了 数据库表，不便于后续维护
        // 解决办法：通过 MP 自定义SQL
        wrapper.setSql("balance = balance - 200");
        // 2、设置 where 条件语句
        wrapper.in("id", 1,2,4);

        userPlusMapper.update(wrapper);
    }


    /**
     * 使用 MyBatis-Plus 【自定义拼接SQL】 的方式实现 案例3
     */
    @Test
    public void testCustomSqlSegment() {
       // 1、创建条件构造器
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        // 2、设置 where 条件语句
        wrapper.in(User::getId, 1,2,4);

        userPlusMapper.updateBalanceByWrapper(200, wrapper);
    }


}
