package com.czm.d1_junit;

import org.junit.*;

public class StringUtilTest {

    /*
     Junit 单元测试框架的常用注解(Junit 4.xxxx版本):
        @Test，测试类中的方法必须用它修饰才能成为测试方法，才能启动执行。
        @Before，用来修饰一个实例方法，该方法会在每一个测试方法执行之前执行一次。 ---> Junit 5.xxxx版本 @BeforeEach
        @After，用来修饰一个实例方法，该方法会在每一个测试方法执行之后执行一次。 ---> Junit 5.xxxx版本 @AftereEach
        @Beforeclass，用来修饰一个静态方法，该方法会在所有测试方法之前只执行一次。---> Junit 5.xxxx版本 @BeforeAll
        @AfterClass，用来修饰一个静态方法，该方法会在所有测试方法之后只执行一次。---> Junit 5.xxxx版本 @AftereAll

        在测试方法执行前执行的方法，常用于：初始化资源。
        在测试方法执行完后再执行的方法，常用于：释放资源。
     */

    @Before
    public void before() {
        System.out.println("=============before ==============");
    }

    @After
    public void after() {
        System.out.println("=============after ==============");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("=============before ==============");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("=============after ==============");
    }

    @Test
    public void testPrintNumber() {
        StringUtil.printNumber("");
        StringUtil.printNumber(null);
        StringUtil.printNumber("abc");
    }

    @Test
    public void testGetMaxIndex() {

//        System.out.println(StringUtil.getMaxIndex(null));
//        System.out.println(StringUtil.getMaxIndex(""));
//        System.out.println(StringUtil.getMaxIndex("abc"));

        // 如上虽然方法都能测试通过，但是结果不是我们期望的，就需要用 Assert 断言，来实现正确的测试用例
        // Assert 断言：预言

        int i1 = StringUtil.getMaxIndex(null);
        Assert.assertEquals("null 测试失败！", -1, i1);

        int i2 = StringUtil.getMaxIndex("");
        Assert.assertEquals("‘’ 字符串测试失败！", -1, i2);

        int i3 = StringUtil.getMaxIndex("abc");
        Assert.assertEquals("字符串abc测试失败！", 2, i3);

    }
}
