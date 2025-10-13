package com.czm.d4_SystemStreams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Test02Scanner {
    /*
      1、Scanner 扫描器 - 了解
       java.util.Scanner 是一个可以使用正则表达式来解析基本类型和字符串的简单文本扫描器。
       它默认利用空白（空格/制表符/行终止符）作为分隔符将输入分隔成多个token。
       有如下初始化方法：
            Scanner(InputStream source)
            Scanner(Readable source)
            Scanner(File source)
            Scanner(String source)

      2、Scanner - useDelimiter
       useDelimiter 方法用于自定义分隔符


     */
    public static void main(String[] args) {

        System.out.println("--- 1、Scanner 扫描字符串的值：");
        Scanner sc = new Scanner("你是最棒的 忽略傻逼 天天开心！ ab12 abc322 a12");
        while (sc.hasNext()) {
//            System.out.println(sc.next());
//            sc.nextInt();
//            sc.nextDouble();

            // 正则匹配
            try {
                System.out.println(sc.next("[a-z]+\\d+"));
            } catch (InputMismatchException e) {
                // 跳过不匹配的token
                sc.next();
            }
        }
        sc.close();

        System.out.println("\n--- 2、Scanner 扫描文件内容：");
        try {
            Scanner sc2 = new Scanner(new File("/Users/chen/Desktop/33.txt"));
            while (sc2.hasNext()) {
                System.out.println(sc2.next());
            }
            sc2.close();
        } catch (FileNotFoundException e) {
        }

        System.out.println("\n--- 3、Scanner 自定义扫描器分隔符：");
        Scanner sc3 = new Scanner("ads12您好12 阿迪 123 阿道夫看");
        sc3.useDelimiter("\\s*\\d+\\s*");
        while (sc3.hasNext()) {
            System.out.println(sc3.next());
        }
        sc3.close();

        System.out.println("\n--- 4、Scanner 从标准输入流读取数据：");
        Scanner sc4 = new Scanner(System.in, "UTF-8");
        System.out.println("请输入第1个整数：");
        int n1 = sc4.nextInt();
        System.out.println("请输入第2个整数：");
        int n2 = sc4.nextInt();
        System.out.printf("%d + %d = %d%n", n1, n2, n1 + n2);
        sc4.close();

        System.out.println("\n--- 5、Scanner 实现控制台问答案例：");
        Scanner sc5 = new Scanner(System.in);
        while (sc5.hasNextLine()) {
            String line = sc5.nextLine();
            line = line.replace("你", "朕");
            line = line.replace("您", "朕");
            line = line.replace("吗", "");
            line = line.replace("么", "");
            line = line.replace("？", "！");
            System.out.println("\t" + line);
        }
        sc5.close();

    }
}
