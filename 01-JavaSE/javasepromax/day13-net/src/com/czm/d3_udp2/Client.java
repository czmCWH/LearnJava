package com.czm.d3_udp2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

    // 实现客户端发送数据，多发多收

    public static void main(String[] args) throws Exception {
        // 1、创建客户端对象
        DatagramSocket socket = new DatagramSocket(6666);   // 默认会分配端口

        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.println("请输入：");
            String msg = sc.nextLine();

            if (msg.equals("exit")) {
                // 4、关闭通信管道，释放资源
                socket.close();
            }

            // 2、创建一个数据包对象，负责封装要发送的数据
            byte[] buffer = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 8888);

            // 3、把数据包发送到服务端
            socket.send(packet);
            System.out.println("--- 客户端已发送完毕！");
        }

    }
}
