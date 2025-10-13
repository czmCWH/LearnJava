package com.czm;

import com.czm.d1_test.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 演示2：@ParameterizedTest + @ValueSource 注解 参数化测试
 */
@DisplayName("演示：注解参数化测试")  // @DisplayName 注解，描述测试类、测试方法的信息，更直观的反应测试。
public class UserService2Test {

    /*
     参数化测试时，会把 @ValueSource 注解接受到的参数数组，一个一个的拿出来传递给测试方法依次执行。
     */

    @DisplayName("测试年龄")
    @ParameterizedTest
    @ValueSource(strings = {"111122199408201110", "111122198408201111", "111122199108201113", "111122199008201112"})
    public void testGetAge(String idcard) {
        // @ValueSource 会多次调用 testGetAge 依次把 strings 的每一个传递给  idcard
        Integer age = new UserService().getAge(idcard);
        System.out.println("age = " + age);
    }

    @DisplayName("测试性别")
    @ParameterizedTest
    @ValueSource(strings = {"11122198408201110", "11122198408201111", "11122198408201113", "11122198408201112"})    // @ValueSource 注解，指定参数来源
    public void testGetGender(String idcard) {
        String gender = new UserService().getGender(idcard);
        System.out.println("gender = " + gender);
    }
}
