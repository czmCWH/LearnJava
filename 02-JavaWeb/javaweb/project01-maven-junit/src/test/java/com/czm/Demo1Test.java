package com.czm;

import org.junit.jupiter.api.*;

/**
 * 演示1：junit 依赖的常用注解
 */
public class Demo1Test {

    @Test
    public void test1() {
        System.out.println("test.....111.....");
    }

    @Test
    public void test2() {
        System.out.println("test.....222.....");
    }

    @BeforeEach
    public void testBeforeEach() {
        System.out.println("BeforeEach.........");
    }

    @AfterEach
    public void testAfterEach() {
        System.out.println("AfterEach.........");
    }

    @BeforeAll
    public static void testBeforeAll() {
        System.out.println("testBeforeAll......");
    }

    @AfterAll
    public static void testAfterAll() {
        System.out.println("testAfterAll......");
    }


}
