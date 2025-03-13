package com.czm.d5_data_stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Test1 {
    /*
     1、特殊数据流 --- 常用于数据通信
        类型：
            a、DataInputStream 特殊数据输入流
                特点：用于读取 数据输出流 写出去的数据。
            b、DataOutputStream 特殊数据输出流
                特点：允许把数据和其类型一并写出去，比如：输入/输出 double类型是的1.23 时，会把类型和值一并读写。

     2、
     */
    public static void main(String[] args) {
        System.out.println("---  1、输出数据到文件");
        dataToFile();
        System.out.println("--- 2、读取数据到内存");
        dataInput();
    }

    // 使用数据输出流 输出数据
    public static void dataToFile() {
        try (
                // 1、创建特殊数据输出流对象
                DataOutputStream dos = new DataOutputStream(new FileOutputStream("day10-io-code/src/data_stream_01.txt"));
        ) {
            // 2、写入数据和其类型到文件中去
            dos.writeInt(1);
            dos.writeChar('A');
            dos.writeByte(97);
            dos.writeBoolean(true);
            dos.writeUTF("好好好学🥰🥰🥰");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 使用数据输入流，读入数据
    public static void dataInput() {
        try (
                // 1、创建高级的特殊数据输入流管道包装低级的字节输入流管道
                DataInputStream dis = new DataInputStream(new FileInputStream("day10-io-code/src/data_stream_01.txt"));
        ) {
            // 2、开始读取数据
            // ⚠️：读取数据的顺序、类型必须与文件中写入时的顺序一致！！！
            int i = dis.readInt();
            System.out.println(i);

            char c = dis.readChar();
            System.out.println(c);

            byte b = dis.readByte();
            System.out.println(b);

            boolean b1 = dis.readBoolean();
            System.out.println(b1);

            String s = dis.readUTF();
            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
