package com.czm.d1_test;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello Maven ~");

        System.out.println(osName());
    }

    public static String osName() {
        return System.getProperty("os.name").toLowerCase();
    }
}
