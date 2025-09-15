package com.czm.d1_enum;

/**
 * 自定义类 模拟枚举效果
 */
//public class Season {
//    // 私有化构造器，避免外部实例化类
//    private Season() {}
//
//    public static final Season SPRING = new Season();
//    public static final Season SUMMER = new Season();
//    public static final Season AUTUMN = new Season();
//    public static final Season WINTER = new Season();
//}
//
/// / 自定义类实现枚举效果
//public static void testSeason(Season season) {
////        switch (season) {     // switch 语句不能使用 引用类型
////            case Season.S
////        }
//    if (season == Season.SPRING) {
//        System.out.println("春天");
//    } else if (season == Season.SUMMER) {
//        System.out.println("夏天");
//    } else if (season == Season.AUTUMN) {
//        System.out.println("秋天");
//    } else if (season == Season.WINTER) {
//        System.out.println("冬天");
//    }
//}


public enum Season {
    SPRING(5, 15), SUMMER(25, 45), AUTUMN(15, 25), WINTER(-5, 15);

    private int min;
    private int max;

    // 自定义枚举的构造方法
    Season(int min, int max) {
        System.out.println("--- 调用了枚举的构造方法");
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}