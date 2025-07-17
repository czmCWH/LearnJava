package com.czm.mapper;

import com.czm.pojo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 基于 MyBatis-Plus 实现 CRUD 操作
 */

@SpringBootTest
public class UserPlusMapperTest {

    @Autowired
    private UserPlusMapper userPlusMapper;

    @Test
    void testInsert() {
        User user = new User();
//        user.setId(5L);
        user.setUsername("Lucy");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userPlusMapper.insert(user);
    }

    @Test
    void testSelectById() {
        User user = userPlusMapper.selectById(5L);
        System.out.println("user = " + user);
    }


    @Test
    void testQueryByIds() {
        List<User> users = userPlusMapper.selectByIds(List.of(1L, 2L, 3L, 4L));
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateById() {
        User user = new User();
        user.setId(5L);
        user.setBalance(25000);
        userPlusMapper.updateById(user);
    }

    @Test
    void testDeleteUser() {
        userPlusMapper.deleteById(5L);
    }
}
