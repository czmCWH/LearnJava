package com.czm;

import com.czm.entity.User;
import com.czm.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * ⚠️⚠️⚠️ 测试类所在的包，需要与启动类(引导类)所在包名相同，不然环境加载报错！！！
 * 解决此类报错：@SpringBootTest(classes = 启动类名称.class)
 */

// @SpringBootTest  注解作用：在单元测试运行时加载 Spring 环境，拿到 IOC容器
@SpringBootTest
public class UserMapperTest {

    @Autowired  // 依赖注入，
    private UserMapper userMapper;

    @Test
    public void testList() {
        List<User> list = userMapper.list();
        System.out.println("---数据库中查询到数据：");
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindList() {
        List<User> list = userMapper.findList();
        System.out.println("--- XML 数据库中查询到数据：");
        for (User user : list) {
            System.out.println(user);
        }
    }
}
