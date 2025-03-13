package com.czm.d6_object_stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test1 {
    /*
     1、IO流：序列化流
        对象序列化：把 Java 对象写入到文件中去
        对象反序列化：把文件里的 Java 对象读出来

     2、ObjectOutputStream 对象字节输出流
        可以把 Java 对象进行序列化，把 Java 对象存入到文件中去。

     */
    public static void main(String[] args) throws Exception {
        System.out.println("------ 1、把对象序列化");
        outputObject();
        System.out.println("------ 2、反序列化对象");
        inputObject();
    }

    public static void outputObject() throws Exception {
        // 1、创建一个 java 对象
        Student st = new Student("张三", 18, "12345", 178.5);

        // 2、创建对象输出流管道与目标文件接通
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("day10-io-code/src/object_01.txt"));

        // 3、开始写对象出去
        oos.writeObject(st);

        // 4、关闭资源
        oos.close();
    }
    public static void inputObject() throws Exception {
        // 1、创建对象字节输入流管道
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("day10-io-code/src/object_01.txt"));
        Student st = (Student) ois.readObject();
        System.out.println(st);
        // 2、关闭管道
        ois.close();
    }
}
