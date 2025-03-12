package com.czm.d2_file;

import java.io.File;
import java.io.IOException;

public class Test3 {
    /*
     1、File 类创建文件、删除文件

     2、
     */
    public static void main(String[] args) throws IOException {

        System.out.println("---- 1、创建文件");
        File file = new File("/Users/chen/Downloads/test.txt");
        if (!file.exists()) {
            System.out.println("--- 文件是否创建成功 = " + file.createNewFile());
        } else {
            System.out.println("--- 文件存在，不用创建");
        }

        System.out.println("---- 2、创建文件夹");
        File dir = new File("/Users/chen/Downloads/test");
        if (!dir.exists()) {
            // mkdir 一级一级的创建
            // mkdirs 可以创建多级
            System.out.println("--- 创建文件夹 = " + dir.mkdir());
        } else {
            System.out.println("--- 文件夹存在，不用创建");
        }

        System.out.println("---- 3、删除文件、空文件夹");
        if (file.exists()) {
            System.out.println("--- 删除文件 = " + file.delete());
        } else {
            System.out.println("---文件不存在，不用删除");
        }
        if (dir.exists()) {
            System.out.println("--- 删除文件夹 = " + dir.delete());
        } else {
            System.out.println("---文件夹不存在，不用删除");
        }



    }
}
