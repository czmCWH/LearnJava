package com.czm.d8_demo;

import java.io.File;
import java.io.IOException;

public class Test2 {

    // 案例二，删除文件夹

    public static void main(String[] args) {
        deleteDir(new File("/Users/CZM/my"));
    }

    public static void deleteDir(File dir) {
        // 1、卫语句判断，不删除的情况
        if (dir == null || !dir.exists()) {
            return;
        }

        // 2、如果是文件直接删除
        if (dir.isFile()) {
            dir.delete();
            return;
        }

        // 3、获取一级文件夹的内容
        File[] files = dir.listFiles();

        // 卫语句判断，没有权限获取文件，直接返回
        if (files == null) {
            return;
        }

        // 4、删除空文件夹
        if (files.length == 0) {
            dir.delete();
            return;
        }

        // 5、非空文件夹，遍历全部一级文件，并删除
        for (File file : files) {
            if (file.isFile()) {
                file.delete();
            } else {
                deleteDir(file);    // 递归删除文件夹
            }
        }

        // 6、一级文件删空了，再把自己删除
        dir.delete();

    }
}
