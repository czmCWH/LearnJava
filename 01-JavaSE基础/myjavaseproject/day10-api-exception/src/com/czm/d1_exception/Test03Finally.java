package com.czm.d1_exception;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Test03Finally {
    /*
     1、finally
     try 或 catch 正常执行完毕后，一定会执行 finally 中的代码；
     finally 可以和 try-catch 搭配使用，也可以只和 try 搭配使用；

        使用场景：经常会在 finally 中编写一些关闭、释放资源的代码(比如：关闭文件)；

     2、finally 细节 ⚠️
     如果在执行 try 或 catch 时，JVM 退出或者当前线程被中断、杀死，finally 可能不会执行。
     如果 try 或 catch 中使用了 return、break、continue 等提前结束语句，finally 会在 return、break、continue 之前执行。

     */

    public static void main(String[] args) {

        PrintWriter out = null;
        try {
            out = new PrintWriter("/Users/chen/Desktop/1.txt");
            out.println("我是上帝！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {     // try 中的代码执行正常，或出现异常后，都会执行 finally
            if (out != null) {
                out.close();
            }
        }

        System.out.println("\n");
        System.out.println("--- try 中执行了 return ，finally 也会执行");
        try {
            System.out.println("--- 1 try");
//            return;
        } finally {
            System.out.println("--- 2 finally");
        }

        System.out.println("\n");
        System.out.println("--- finally 和 continue：");
        for (int i = 1; i <= 3; i++) {
            try {
                System.out.println("--- 1_try: " + i);
                if (i == 2) continue;   // continue 跳过当前循环的‌剩余代码‌，直接进入下一次循环迭代
                System.out.println("--- 2_try: " + i);
            } finally {
                System.out.println("--- finally: " + i);
            }
        }

        System.out.println("\n");
        System.out.println("--- finally 和 break：");
        for (int i = 1; i <= 3; i++) {
            try {
                System.out.println("--- 1_try: " + i);
                if (i == 2) break;  // break 直接跳出循环体
                System.out.println("--- 2_try: " + i);
            } finally {
                System.out.println("--- finally: " + i);
            }
        }

        System.out.println("\n");
        System.out.println(get());      // 依次打印：3、5、4
    }

    public static int get() {
        try {
            new Integer("abc");
            System.out.println(1);
            return 2;
        } catch (NumberFormatException e) {
            System.out.println(3);
            return 4;
        } finally {
            System.out.println(5);
        }
    }
}
