package com.czm;

import com.czm.d1_test.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 单元测试企业开发规范
 */
public class UserService4Test {
    private UserService userService;

    @BeforeEach
    public void setup() {
        userService = new UserService();
    }

    /**
     * 测试获取性别 - null
     */
    @Test
    @DisplayName("获取性别 - null值")
    public void testGetGender1() {
        // IllegalArgumentException 无效参数异常
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getGender(null));
    }


    /**
     * 测试获取性别 - ""
     */
    @Test
    @DisplayName("获取性别 - 空字符串")
    public void testGetGender2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getGender(""));
    }

    /**
     * 测试获取性别 - 长度不足
     */
    @Test
    @DisplayName("获取性别 - 长度不足")
    public void testGetGender3() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getGender("110"));
    }


    /**
     * 测试获取性别 - 超出长度
     */
    @Test
    @DisplayName("获取性别 - 长度超出")
    public void testGetGender4() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getGender("1101101231231121101010"));
    }


    /**
     * 测试获取性别 - 正常：男
     */
    @Test
    @DisplayName("获取性别 - 正常男性身份证")
    public void testGetGender5() {
        String gender = userService.getGender("100110101010111111");
        Assertions.assertEquals("男", gender);
    }

    /**
     * 测试获取性别 - 正常：女
     */
    @Test
    @DisplayName("获取性别 - 正常女性身份证")
    public void testGetGender6() {
        String gender = userService.getGender("100110101010111121");
        Assertions.assertEquals("女", gender);
    }

    // ----------------------- 测试获取年龄

    /**
     * 测试获取年龄 - 正常
     */
    @Test
    @DisplayName("获取年龄 - 正常身份证")
    public void testGetAge() {
        Integer age = userService.getAge("100110199408141111");
        Assertions.assertEquals(31, age);
    }

    /**
     * 测试获取年龄 - null值
     */
    @Test
    @DisplayName("获取年龄 - null值")
    public void testGetAge2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getAge(null));
    }

    /**
     * 测试获取年龄 - 超长
     */
    @Test
    @DisplayName("获取年龄 - 长度超长")
    public void testGetAge3() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getAge("1001101994081411112"));
    }

    /**
     * 测试获取年龄 - 长度不足
     */
    @Test
    @DisplayName("获取年龄 - 长度不足")
    public void testGetAge4() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.getAge("110"));
    }

}
