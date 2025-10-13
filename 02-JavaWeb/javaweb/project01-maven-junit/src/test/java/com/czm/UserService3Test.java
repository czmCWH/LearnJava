package com.czm;

import com.czm.d1_test.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试类，用于测试 UserService 业务方法，规范的命名方式为 “类名 + Test” 或者 “Test + 类名”
 * 演示3：@Test 和 断言
 */
public class UserService3Test {
    @Test
    public void testGetAge() {
        Integer age = new UserService().getAge("111122198408201111");
        System.out.println("age = " + age);
    }

    @Test
    public void testGetGender() {
        String gender = new UserService().getGender("111122198408201111");
//        System.out.println("gender = " + gender);
        assertEquals("男", gender, "性别获取错误！");
        assertEquals("女", gender, "性别获取失败！");
        assertEquals("男", gender, "性别获取成功！");
//        Assertions.assertThrows()
    }

    @Test
    public void testGetGender2() {
        UserService userService = new UserService();
        // 断言，抛出的异常是否和预期异常类型一致
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getGender(null);
        });
    }

}
