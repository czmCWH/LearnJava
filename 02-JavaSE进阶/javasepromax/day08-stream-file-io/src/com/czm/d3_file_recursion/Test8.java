package com.czm.d3_file_recursion;

import java.io.File;

public class Test8 {
    /*
     1、递归案列 - 文件搜索
        搜索某个目录下的文件，并输出其存在的目录位置

     2、
     */
    public static void main(String[] args) {
        String home = System.getProperty("user.home");
//        System.out.println("用户主目录 = " + home);
        searchFile(new File(home + File.separator + "Downloads"), "WechatIMG");

    }

    public static void searchFile(File dir, String fileName) {
        // 1、极端判断
        if (dir == null || !dir.exists() || dir.isFile()) {
            return;
        }

        // 2、开始搜索
        // 用户思维 > 一级一级的往下查找

        // 3、拿到1级文件对象数组
        File[] files = dir.listFiles();

        // 4、文件夹没有权限 or 文件夹是空的
        if (files == null || files.length == 0) {
            return;
        }

        // 5、遍历全部一级文件对象
        for (File file : files) {
            if (file.isFile()) {
                // 文件
                if (file.getName().contains(fileName)) {
                    System.out.println(file.getAbsolutePath());
                }
            } else {
                // 文件夹
                searchFile(file, fileName);
            }
        }
    }
}
