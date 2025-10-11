package com.czm.d2_network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 使用 Socket 模拟一个 TCP 服务器
 * socket：插座，等待连接
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        // 计算机网络协议的端口号为16位，最小值为：0，最大值为：1111 1111 1111 1111，即：2^16 - 1
        System.out.printf("计算机网络协议的端口号为16位，取值范围为：%d~%d%n", 0, 0xFFFF);   // ：0~65535

//        testOnceServer();
        // 服务器保持运行状态，一直接收客户端请求
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            while (true) {
//                try (
//                    Socket client = serverSocket.accept();  // 等待客户端连接，阻塞式等待
//                ) {
//                    doClient(client);
//                }

                // 多个线程单独处理请求
                Socket client = serverSocket.accept();  // 等待客户端连接，阻塞式等待
                new Thread(() -> {
                    try {
                        doClient(client);
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }

    }

    private static void doClient(Socket client) throws IOException {
        try (
                InputStream is = client.getInputStream();   // 读取客户端传递的数据
                ByteArrayOutputStream baos = new ByteArrayOutputStream();   // 接收读取的数据
                OutputStream os = client.getOutputStream();
        ) {
            // 1、从客户端读取数据
            byte[] buffer = new byte[8192];
            int len;

            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            byte[] bytes = baos.toByteArray();  // 拿到所有数据

            String msg = new String(bytes, StandardCharsets.UTF_8);
            String ip = client.getInetAddress().getHostAddress();
            System.out.printf("Server 接收到 [%s] 的数据：%s%n", ip, msg);

            // 2、向客户端发送数据
             os.write(doString(msg).getBytes(StandardCharsets.UTF_8));
        }
    }

    private static String doString(String str) {
        str = str.replace("你", "朕");
        str = str.replace("您", "朕");
        str = str.replace("吗", "");
        str = str.replace("么", "");
        str = str.replace("？", "！");
        return str;
    }

    // 服务器接收到一个请求执行完毕后，服务器就关闭
    private static void testOnceServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket client = serverSocket.accept();  // 等待客户端连接，阻塞式等待

        // 读取客户端传递的数据
        InputStream is = client.getInputStream();
        byte[] buffer = new byte[8192];
        int len;
        // 接收读取的数据
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        byte[] bytes = baos.toByteArray();  // 拿到所有数据

        String msg = new String(bytes, StandardCharsets.UTF_8);
        System.out.printf("接收到 [%s] 的数据：%s%n", client.getInetAddress(), msg);

        is.close();
        baos.close();
        client.close();
        serverSocket.close();
    }
}
