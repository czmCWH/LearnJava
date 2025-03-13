package com.czm.d2_try_finally;

public class Test1 {
    /*
     1、try-catch-finally
        finally 代码区的特点：无论 try 中的程序是正常执行了，还是出现了异常，最后都一定会执行 finally 区，除非 JVM 终止。
        作用：一般用于在程序执行完成后进行资源的释放操作（⚠️专业级做法）。
        语法格式：
            try {
            } catch (Exception e) {
            } finally {
            }
     */
    public static void main(String[] args) {

        System.out.println(divide());  // 打印：100
    }

    public static int divide() {
        try {
            return 10/2;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            return 100; // ⚠️：不要在finally中返回数据，会覆盖前面所有的数据!
        }
    }

}
