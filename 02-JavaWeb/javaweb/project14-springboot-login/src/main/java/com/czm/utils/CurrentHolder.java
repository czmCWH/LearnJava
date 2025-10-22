package com.czm.utils;

/**
 * ThreadLocal 工具类 - 用于存储用户上下文信息：用户ID
 */
public class CurrentHolder {

    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    /**
     * 使用 ThreadLocal 存储员工ID
     */
    public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }

    /**
     * 使用 ThreadLocal 获取员工ID
     */
    public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }

    /**
     * 删除 ThreadLocal 中存储的用户ID
     */
    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
