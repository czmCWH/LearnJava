package com.czm.d1_inetAddress;

import java.net.InetAddress;
import java.sql.SQLOutput;

public class Test1 {
    /*
     1、InetAddress 代表IP地址。

     2、

     3、
     */
    public static void main(String[] args) throws Exception {
        // 1、获取本机IP地址对象
        InetAddress ip = InetAddress.getLocalHost();
        System.out.println("本机的主机地址：" + ip.getHostAddress());
        System.out.println("本机的主机名：" + ip.getHostName());

        // 2、根据ip地址或者域名，返回一个inetAdress对象
        InetAddress ip2 = InetAddress.getByName("www.baidu.com");
        System.out.println("百度的主机地址：" + ip2.getHostAddress());
        System.out.println("百度的主机名：" + ip2.getHostName());

        // 3、判断主机与网络上的主机能否联通，即：ping 操作
        System.out.println(ip2.isReachable(5000));      // 5秒内能否联通

        // 例如：实现短信预警服务器

    }
}
