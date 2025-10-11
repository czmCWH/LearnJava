package com.czm.d2_file_x;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Test2 {
    /*
     1、File 提供的判断文件类型、获取文件信息功能
        public boolean exists()     判断文件夹 or 文件 是否存在，true：存在
        public boolean isFile()     判断文件对象是否是文件，true：是文件
        public boolean idDirectory()        判断文件对象是否是文件夹，true：是文件夹
        public String getName()
        public long length()
        p
     2、
     */
    public static void main(String[] args) {

        File f = new File("/Users/chen/Downloads/RPReplay_Final1741166075 (1).mp4");

        System.out.println("--- 1、判断文件夹 or 文件 是否存在 = " + f.exists());
        System.out.println();

        System.out.println("--- 2、是否是文件 = " + f.isFile());
        System.out.println();

        System.out.println("--- 3、是否是文件夹 = " + f.isDirectory());
        System.out.println();

        System.out.println("--- 4、获取文件名包含后缀 = " + f.getName());
        System.out.println();

        System.out.println("--- 5、文件对象大小字节数 = " + f.length());
        System.out.println();

        long time = f.lastModified();
        System.out.println("--- 6、文件最后修改时间 = " + time);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        String fmtTime = fmt.format(dateTime);
        System.out.println(fmtTime);
        System.out.println();

        System.out.println();
        File lf = new File("day08-stream-file-io/src/WechatIMG6123.jpg");
        System.out.println("--- 7、文件对象的相对路径 = " + lf.getPath());

        System.out.println();
        System.out.println("--- 8、文件对象的绝对路径 = " + lf.getAbsolutePath());

        File pf = new File("");
        System.out.println("--- 获取工程路径 = " + pf.getAbsolutePath());


    }
}
