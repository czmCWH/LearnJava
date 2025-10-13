package com.czm.d1_File;

import java.io.File;
import java.util.Arrays;

public class Test01File {

    /*
     1、File
      一个 File 对象就代表一个文件或目录（文件夹）。
      File 类只能对文件本身进行操作，不能读写文件里面存储的数据。
      File封装的对象仅仅是一个路径名，这个路径可以是存在的，也允许是不存在的。

     2、名字分隔符（name separator）
      名字分隔符 是指文件路径使用字符串表示时，文件夹与文件之间的分隔符。
      在 UNIX、Linux、Mac 系统中使用的是 正斜杠(/)；而在 Windows 中应使用 反斜杠(\)。
      由于 Java 中使用 反斜杠(\) 表示转义，因此需使用 双反斜杠(\\)。

      File.separator，获取系统使用名字分隔符。

     3、路径分隔符（path separator）
      路径分隔符 是指 在字符串中表示多个路径时，使用的分隔符。
      在 UNIX、Linux、Mac 系统中使用 冒号(:) ，如 "\User\1.txt:\Desktop\2.txt"；
      在 Windows 系统中使用 分号(;)，如："F:\\Files\1.txt;D:\\doc\\1.txt"；


      File.pathSeparator，获取系统使用路径分隔符

     4、注意事项
      在 Windows、Mac 系统中，文件名、目录名不区分大小写；
      在 UNIX、Linux 系统中，文件名、目录名区分大小写；

     5、File - 常用方法
        String getName()，获取文件或目录的名称
        String getParent()，获取父路径
        File getParentFile()，获取父路径 File
        long lastModified()，最后一次修改时间
        long length()，获取文件的大小（不支持目录）

        String getPath()，获取相对路径
        String getAbsolutePath()，获取绝对路径
        File getAbsoluteFile()，获取绝对路径形式的文件

        boolean isAbsolute()，是否是绝对路径
        boolean exists()，是否存在
        boolean isDirectory()，是否是目录
        boolean isFile()，是否是文件
        boolean isHidden()，是否隐藏
        boolean canRead()，
        boolean canWrite()，

        String[] list()，获取当前目录下所有文件、目录名称
        String[] list(FilenameFilter filter)，
        File[] listFiles()，获取当前目录下所有文件、目录
        File[] listFiles(FilenameFilter filter)，
        File[] listFiles(FileFilter filter)

        boolean createNewFile()，创建文件（不会覆盖旧文件）
        boolean delete()，删除文件或空目录（不会经过垃圾桶）
        boolean mkdir()，创建当前目录
        boolean mkdirs()，创建当前目录（包括不存在的父目录）
        boolean renameTo(File dest)，剪切到新路径，file.renameTo(new File(/User/2.txt))，把 file 剪切移动到 2.txt。

     6、不常用方法
        boolean setLastModified(long time)
        boolean setReadOnly()
        boolean setWritable(boolean writable, boolean ownerOnly)
        boolean setWritable(boolean writable)
        boolean setReadable(boolean readable, boolean ownerOnly)
        boolean setReadable(boolean readable)

     7、listFiles() 注意事项
        主调 <=> 调用 listFiles 的File 对象
        a、当主调的文件 or 路径不存在时，返回 null；
        b、当主调是空文件夹时，返回一个长度为 0 的数组；
        c、当主调是一个有内容的文件夹时，将里面所有一级文件和文件夹的路径放在 File 数组中返回；
        d、当主调是一个文件夹，且里面有隐藏文件时，将里面所有文件和文件夹的路径放在File数组中返回，包含隐藏文件
        e、当主调是一个文件夹，但是没有权限访问该文件夹时，返回 null

     */

    public static void main(String[] args) {

        // 获取当前系统使用的 名字分隔符
        System.out.println(File.separator);   // /

        File file1 = new File("C:\\Users\\czm\\Desktop\\1.txt");
        File file2 = new File("C:/Users/czm/Desktop/1.txt");
        File file3 = new File("C:" + File.separator + "Users" + File.separator + "czm" + File.separator + "Desktop"  + File.separator + "1.txt");

        // 获取当前系统使用的 路径分隔符
        System.out.println(File.pathSeparator); // :


        System.out.println("\n--- File 常用方法：");

        System.out.println("\n--- 绝对路径文件：");
        File file = new File("/Users/chen/Desktop/11.txt");
        if (!file.exists()) {
            System.out.println("文件不存在！");
            return;
        }
        System.out.println("获取文件/目录名称，getName = " + file.getName());     // 11.txt
        System.out.println("获取父路径，getParent = " + file.getParent());     // /Users/chen/Desktop
        File parentFile = file.getParentFile();
        System.out.println("获取父文件，getParentFile = " + parentFile);  // /Users/chen/Desktop
        System.out.println("获取文件大小（不支持目录），length = " + file.length()); // 16，
        System.out.println("文件最后一次修改时间戳，lastModified = " + file.lastModified());    //  1758072787444

        System.out.println("\n--- 相对路径文件：");
        // 相对路径：默认相对到相当工程下寻找文件的，即直接到project(工程)下找文件
        // 一般是用来查找项目中的资源文件
//        File fl = new File("22.txt");   // 获取工程根目录下的 22.txt 文件
        File fl = new File("day12-api-io/src/22.txt");   // 获取指定模块下的 22.txt 文件
        if (!fl.exists()) {
            System.out.println("文件不存在！");
            return;
        }
        System.out.println("获取相对路径：" + fl.getPath());   // day12-api-io/src/22.txt
        System.out.println("获取绝对路径： " + fl.getAbsolutePath());  // /Users/CZM/Git/github/LearnJava/01-JavaSE基础/myjavaseproject/day12-api-io/src/22.txt

        // 获取绝对路径形式的文件
        File absoluteFl = fl.getAbsoluteFile();
        System.out.println("获取父路径，getParent = " + absoluteFl.getParent());

        fileList();


    }

    static void fileList() {
        System.out.println("\n--- list 相关方法：");

        File file = new File("/Users/chen");
        String[] names = file.list();
        System.out.println("获取当前目录下所有文件、目录名称：list = " + Arrays.toString(names));

        String[] names2 = file.list((dir,name) -> {
//           return !name.startsWith(".");
           return name.matches("^[^.].+");
        });
        System.out.println("根据条件过滤当前目录下所有文件、目录名称：list = " + Arrays.toString(names2));

    }
}
