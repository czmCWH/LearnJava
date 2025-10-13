package com.czm.d2_network;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * TCP 客户端，发送 TCP 请求
 */
public class TCPClient {
    public static void main(String[] args) throws Exception {
        try (
                Socket socket = new Socket("127.0.0.1", 8888);
                OutputStream os = socket.getOutputStream(); // 1、向服务器发送数据
                InputStream is = socket.getInputStream();   // 2、接收服务器发送的数据
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            os.write("你好,".getBytes(StandardCharsets.UTF_8));
            os.write("大西瓜!".getBytes(StandardCharsets.UTF_8));
            // 关闭输出，表示传递给服务器的数据结束
            socket.shutdownOutput();

            byte[] buffer = new byte[8192];
            int len;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            byte[] bytes = baos.toByteArray();  // 拿到所有数据

            String msg = new String(bytes, StandardCharsets.UTF_8);
            String ip = socket.getInetAddress().getHostAddress();
            System.out.printf("Client 接收到 [%s] 的数据：%s%n", ip, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
