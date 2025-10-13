package com.czm.d7_commons_io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class Test1 {
    /*
     1、框架
        解决某类问题，编写的一套类、接口等，可以理解成一个半成品，大多框架都是第三方研发的。
        好处：在框架的基础上开发，可以得到优秀的软件架构，并能提高开发效率。
        框架的形式：一般是把类、接口等编译成 class 形式，再压缩成一个 .jar 结尾的文件发行出去。

     2、IO 框架
        封装了 java 提供的对文件、数据进行操作的代码，对外提供了更简单的方式来对文件进行操作，对数据进行读写等。
        常用 IO 框架：commons-io
        commons-io 是 apache 开源基金组织提供的一组有关 I0 操作的小框架，目的是提高I0流的开发效率。

        导入框架到项目中去：
            1、直接下载 commons-io-2.18.0-bin.zip，注意下载 `-bin.zip` 开发包即可；
            2、下载解压，选择 commons-io-2.18.0.jar 核心开发包复制；
            3、选择项目模块 => 新建【目录】lib => 复制粘贴到 lib 目录下；
            4、选择【目录】lib or jar文件 => 右键选择【添加为库】；
            5、在需要使用的类中导包，开始可以使用了；
     3、
     */
    public static void main(String[] args) throws Exception {
        // 使用 commons-io 框架

        // 1、复制文件
//        FileUtils.copyFile(new File("/Users/CZM/学习视频/day10-IO(二)/13、IO框架.mp4"), new File("/Users/CZM/学习视频/video/1.mp4")); // 注意如果 video 目录不存在会自动创建
        // 2、复制文件夹里的内容
//        FileUtils.copyDirectory(new File("/Users/CZM/学习视频/day10-IO(二)"), new File("/Users/CZM/学习视频/video"));
        // 3、删除文件夹
//        FileUtils.deleteDirectory(new File("/Users/CZM/学习视频/video"));   // ⚠️：删除直接清理了，不会丢到垃圾桶

        // JDK7 开始也新增了单行代码相关的操作
        Files.copy(Path.of("/Users/CZM/学习视频/day10-IO(二)/13、IO框架.mp4"), Path.of("/Users/CZM/学习视频/video/1.mp4")); // 注意如果 video 目录不存在会报错
    }
}
