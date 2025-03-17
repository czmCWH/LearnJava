package com.czm.d3_udp2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    // 服务端接收数据，多发多收

    public static void main(String[] args) throws Exception {
        // 1、创建接收端对象，注册端口
        DatagramSocket socket = new DatagramSocket(8888);

        while (true) {
            // 2、创建一个数据包对象，接收存放的数据，最大64kb
            byte[] buffer = new byte[1024 * 64];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // 3、接收数据
            socket.receive(packet); // ⚠️ 未收到数据时，会暂停等待！

            // 4、把数据输出
            int len = packet.getLength();   // 一共收到了多少数据
            String s = new String(buffer, 0, len);
            System.out.println("--- 服务端接收到了 = " + s);

            // 获取发送端IP和端口
            InetAddress ip = packet.getAddress();
            System.out.println("对方IP = " + ip.getHostAddress());
            System.out.println("对方端口 = " + packet.getPort());

            // 5、释放资源
//            socket.close();   // 需一直等待接收数据，不能关闭
        }

    }
}
