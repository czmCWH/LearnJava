package com.czm.d8_demo;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class Test1 {
    /*
     案例一，复制文件夹
     */
    public static void main(String[] args) throws Exception {
        // 源文件夹位置：
        // 目标位置：
        copyDirectory(new File("/Users/CZM/学习视频"), new File("/Users/CZM/my"));

    }
    // 复制源文件夹，到目标文件夹
    public static void copyDirectory(File srcDir, File destDir) throws Exception {
        if (srcDir == null || destDir == null || !srcDir.exists() || !destDir.exists() || srcDir.isFile() || destDir.isFile()) {
            System.out.println("--- 文件夹参数异常");
            return;
        }
        // 1、先在目标目录下创建与拷贝文件夹同名的目录
        File destNewDir = new File(destDir, srcDir.getName());
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        // 2、提取源目录中的一级文件对象
        File[] files = srcDir.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("--- files 为 null 表示无权限；length 为 0 表示没有文件");
            return;
        }

        // 3、遍历全部一级文件对象，拷贝到目标文件夹目录
        for (File file : files) {
            if (file.isFile()) {    // 复制文件
                FileUtils.copyFile(file, new File(destNewDir, file.getName()));
            } else {        // 复制文件夹，递归复制
                copyDirectory(file, destNewDir);
            }
        }

    }
}
