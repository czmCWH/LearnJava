package com.czm.d2_xml;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.List;

public class Test3 {
    /*
     案例：使用 Dom4j 框架，将 demo2.xml 文件中的联系人数据，解析出来，封装成List集合，并遍历输出

     */
    public static void main(String[] args) throws Exception {
        // 1、创建一个 SAXReader 解析器对象
        SAXReader reader = new SAXReader();

        // 2、把xml文件读成一个 Document 文档对象
        Document doc = reader.read("day11-special-file-log-code/src/demo2.xml");

        // 3、文本对象包含 XML 所有数据，提供了获取数据的方法
        Element root = doc.getRootElement();

        // 4、准备联系人集合
        List<Contact> list = new ArrayList<>();

        // 5、提取全部的一级元素
        List<Element> elements = root.elements("contact");
        // 遍历子元素
        for (Element sonEle : elements) {
            // 6、把子元素转化为 Contact 对象并存入到集合中
            Contact contact = new Contact();
            contact.setId(Integer.valueOf(sonEle.attributeValue("id")));
            contact.setName(sonEle.elementTextTrim("name"));
            contact.setGender(sonEle.elementTextTrim("gender").charAt(0));
            contact.setEmail(sonEle.elementTextTrim("email"));
            contact.setVip(Boolean.valueOf(sonEle.elementTextTrim("vip")));

            // 7、存入集合
            list.add(contact);
        }
        System.out.println(list);
    }
}
