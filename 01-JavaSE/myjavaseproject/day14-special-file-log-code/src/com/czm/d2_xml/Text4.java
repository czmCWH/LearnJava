package com.czm.d2_xml;

import java.io.PrintStream;

public class Text4 {
    /*
     1、把程序数据写入到 XML 文件中
        不建议使用 Dom4j 框架执行写入操作，因为API比较复杂，需要创建很多节点对象。
        推荐：直接把数据拼接为 XML 格式，用 IO流 写入文件中。
     */
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        sb.append("<user>\r\n");
        sb.append("\t<name>").append("张三").append("</name>\r\n");
        sb.append("\t<age>").append("28").append("</age>\r\n");
        sb.append("\t<gender>").append("女").append("</gender>\r\n");
        sb.append("</user>\r\n");

        PrintStream ps = new PrintStream("day11-special-file-log-code/src/demo3.xml");
        ps.println(sb.toString());
        ps.close();
    }
}
