package com.czm.d5_dataObjStream;

import java.io.*;

public class Test01Data {
    /*
      1、数据流 --- 常用于数据通信
       DataInputStream、DataOutputStream 数据流支持基本类型、字符串类型的 I/O 操作。
       DataInputStream 写入的数据以 特殊方式编码存储，无法直接查看内容。

       特点：
        DataOutputStream 写入的基本类型数据，可以通过 DataInputStream 按照写入的顺序读取相对应类型的数据。
       缺点：
        需要一个一个的写入基本类型的数据，并且按顺序和类型一个一个读取，非常麻烦。

     */
    public static void main(String[] args) {

        int age = 18;
        double height = 1.75;
        String name = "Jack";

//        try (PrintWriter writer = new PrintWriter(new File("/Users/chen/Desktop/1.txt"));) {
//            writer.println(age);
//            writer.println(height);
//            writer.println(name);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//

//        System.out.println("\n--- 1、DataOutputStream 写入基本类型数据：");
//        try {
//            DataOutputStream dos = new DataOutputStream(new FileOutputStream("/Users/chen/Desktop/2.txt"));
//            dos.writeInt(age);
//            dos.writeDouble(height);
//            dos.writeUTF(name);
//            dos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("\n--- 2、DataInputStream 根据写入的顺序读取基本类型数据：");
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(new File("/Users/chen/Desktop/2.txt")));
            System.out.println(dis.readInt());
            System.out.println(dis.readDouble());
            System.out.println(dis.readUTF());
            dis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
