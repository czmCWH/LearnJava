package com.czm.d1_junit_x;

/**
 * 字符串工具类
 *  这个工具类包含很多业务方法，需要使用 junit 进行正确性测试！
 */
public class StringUtil {

    /**
     * 打印字符串的长度
     */
    public static void printNumber(String name) {
        if (name == null) {
            System.out.println("参数不能为null");
            return;
        }
        System.out.println("名字长度是" + name.length());
    }

    /**
     * 获取字符串的最大索引
     */
    public static int getMaxIndex(String str) {
        if (str == null) {
            return  -1;
        }
        return str.length() - 1;
//        return str.length();
    }
}
