package com.czm.d2_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    // 实现客户端发送数据，服务端接收，一发一收
    // DatagramSocket: 用于创建客户端、服务端

    public static void main(String[] args) throws Exception {
        // 1、创建客户端的socket对象，系统会随机分配一个端口号。
        DatagramSocket socket = new DatagramSocket(6666);   // 默认会分配端口

        // 2、创建一个数据包对象，负责封装要发送的数据
        /*
         参数一: 要发送的数据，字节数组
         参数二: 发送的数据大小
         参数三: 目的地IP地址
         参数四: 接收端端口号
         */
        byte[] buffer = "今晚一起喝啤酒、吃龙虾，约吗？".getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 8888);

        // 3、把数据包发送到服务端
        socket.send(packet);

        // 4、关闭通信管道，释放资源
        socket.close();

        System.out.println("--- 客户端已发送完毕！");
    }
}
