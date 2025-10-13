package com.czm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czm.pojo.entity.User;
import com.czm.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;


    /**
     * 批量插入   10 万条数据插入
     */
    @Test
    public void testOneByOne() {
        // 记录开始时间
        Long start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            userService.save(buildUser(i));
        }

        // 记录结束时间
        Long end = System.currentTimeMillis();
        System.out.println("------ 耗时：" + (end - start) + "ms 毫秒");
    }


    /**
     * 分页批量插入数据 ----- 性能更高
     */
    @Test
    public void testBatch() {
        // 记录开始时间
        Long start = System.currentTimeMillis();

        List<User> list = new ArrayList<>(1000);

        for (int i = 0; i < 100000; i++) {
            list.add(buildUser(i));
            if (i % 1000 == 0) {
                userService.saveBatch(list);
                list.clear();
            }
        }

        // 记录结束时间
        Long end = System.currentTimeMillis();
        System.out.println("------ 耗时：" + (end - start) + "ms 毫秒");
    }


    private User buildUser(int i) {
        User user = new User();
//        user.setId(5L);
        user.setUsername("Lucy" + 1);
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return user;
    }

    // 测试 MyBatis-Plus 分页插件
    @Test
    public void testPage() {
        // 1、创建一个分页对象
        // 参数1：当前页码，从1开始
        // 参数2：每页条数
        Page<User> page = new Page<>(3, 2);     // 每页查询2条，查询第3页的数据

        // 2、分页查询
        Page<User> userPage = userService.page(page, null);

        System.out.println("--- 总页数 = " + userPage.getPages());
        System.out.println("--- 总记录数 =" + userPage.getTotal());
        for (User user : userPage.getRecords()) {
            System.out.println(user);
        }

    }
}
