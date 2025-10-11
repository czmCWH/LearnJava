package com.czm.d2_file_x;

import java.io.File;

public class Test1 {
    /*
     1、File
        File 是 java.io.包下的类， File 类的对象，用于代表当前操作系统的文件(可以是文件、或文件夹)。
        注意：
	        File 类只能对文件本身进行操作，不能读写文件里面存储的数据。
	        File封装的对象仅仅是一个路径名，这个路径可以是存在的，也允许是不存在的。
     2、
     */
    public static void main(String[] args) {
        // 1、创建 File 对象
        File f1 = new File("/Users/chen/Downloads/WechatIMG6123.jpg");

        // 文件路径拼接方式
//        String home = System.getProperty("user.home");
//        File f = new File(home + File.separator + "Downloads" + File.separator + "WechatIMG6123.jpg");
        // windows 的路径：“E:\\resource\\1.png” or "E:/resource/1.png"

        System.out.println("---- 文件字节数 = " + f1.length());

        // 2、File 对象可以是文件夹
        File dir = new File("/Users/chen/Downloads");
        System.out.println("文件夹本身的大小 = " + dir.length());   // 不是文件夹里所有内容的大小

        // 3、File 代表的路径也可以不存在
        File noF = new File("/Users/chen/Downloads/test");
        System.out.println("--- 不存在的文件夹，便于后续创建 = " + noF.exists());

        // 4、File 支持相对路径（重点）
        // 相对路径：默认相对到相当工程下寻找文件的，即直接到project(工程)下找文件
        // 一般是用来查找项目中的资源文件
        File lf = new File("day08-stream-file-io/src/WechatIMG6123.jpg");
        System.out.println("--- 工程中的相对路径 = " + lf.length());

    }
}
