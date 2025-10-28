package com.czm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.czm.pojo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * MyBatis-Plus 的工具类 Db 实现增删改查
 */
@SpringBootTest
public class DbTest {

    // 1、根据ID查询用户信息
    @Test
    public void testQueryById() {
        User user = Db.getById(2L, User.class);
        System.out.println(user);
    }

    // 2、查询名字中包含 o 且余额大于等于1000 的用户
    @Test
    public void testQueryByNameAndBalance() {
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.like(User::getUsername, "o")
//                .ge(User::getBalance, 1000);
//        List<User> userList = Db.list(wrapper);

        List<User> userList = Db.lambdaQuery(User.class)
                .like(User::getUsername, "o")
                .ge(User::getBalance, 1000)
                .list();

        for (User user : userList) {
            System.out.println(user);
        }
    }

    // 3、更新用户名为 Rose 的余额为 3500
    @Test
    public void testUpdate() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, "Rose");

        User user = new User();
        user.setBalance(2000);

        Db.update(user, wrapper);

        //Db.lambdaUpdate(User.class).set(User::getBalance, 2000).eq(User::getUsername, "Rose");

    }
}
