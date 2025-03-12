package com.czm.d2_file;

import java.io.File;

public class Test4 {
    /*
     1、File 遍历文件夹
        f.list()
        f.listFiles()

     2、listFiles() 注意事项
        主调 <=> 调用 listFiles 的File 对象
        a、当主调的文件 or 路径不存在时，返回 null；
        b、当主调是空文件夹时，返回一个长度为 0 的数组；
        c、当主调是一个有内容的文件夹时，将里面所有一级文件和文件夹的路径放在 File 数组中返回；
        d、当主调是一个文件夹，且里面有隐藏文件时，将里面所有文件和文件夹的路径放在File数组中返回，包含隐藏文件
        e、当主调是一个文件夹，但是没有权限访问该文件夹时，返回 null
     */
    public static void main(String[] args) {
        // 1、获取当前目录下所有的"一级文件名称"到一个字符串数组中去返回。
        System.out.println();
        System.out.println("--- 1、 public String[] list()");
        File f1 = new File("/Users/chen/Downloads");
        String[] paths = f1.list();
        for (String path : paths) {
            System.out.println(path);
        }

        System.out.println();
        System.out.println("--- 2、public File[] listFiles()");
        // 2、获取当前目录下所有的"-级文件对象"到一个文件对象数组中去返回(重点)
        File[] files = f1.listFiles();
        for (File file : files) {
            System.out.println(file.getAbsoluteFile());
        }
    }
}
