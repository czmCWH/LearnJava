package com.czm.d2_xml;


import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

public class Test2 {
    /*
     1、解析 XML 文件
        是指使用程序读取 XML 文件中的数据。
        注意：程序员并不需要自己写原始的 IO流 代码来解析XML，难度较大!也相当繁琐!

     2、使用 Dom4J 第三方框架解析 XML 文件
        a、下载 Dom4j 框架，官网下载 dom4j-2.1.4.jar。<https://dom4j.github.io/>
        b、在项目中创建一个文件夹:lib
        c、将 dom4j-2.1.4.jar 文件复制到 lib 文件夹
        d、在jar文件上点右键，选择 Add as Library ->点击OK
        e、在类中导包使用

     3、Dom4j解析 XML 文件的思想：文档对象模型
        SAXReader(解析器)
            Document(整个文档)
                Element(元素、标签)
                    Attribute(属性)
                    子元素、文本

     */
    public static void main(String[] args) throws Exception {
        // 1、创建一个 SAXReader 解析器对象
        SAXReader reader = new SAXReader();

        // 2、把xml文件读成一个 Document 文档对象
        Document doc = reader.read("day11-special-file-log-code/src/demo1.xml");

        // 3、文本对象包含 XML 所有数据，提供了获取数据的方法
        Element root = doc.getRootElement();
        System.out.println("---根元素 = " + root.getName());

        // 4、提取子元素对象
        List<Element> sonRoot = root.elements("user");
        for (Element sonElement : sonRoot) {
            System.out.println("--- 子元素名称 = " + sonElement.getName());
        }

        // 5、指定获取单个子元素
        Element userEle = root.element("user");
        // 通过文本元素拿到元素文本值
        System.out.println(userEle.elementText("name"));
        System.out.println(userEle.elementText("sex"));

        // 6、获取子元素的属性对象
        Attribute idAttr = userEle.attribute("id");
        System.out.println(idAttr.getName());
        System.out.println(idAttr.getValue());

        // 直接拿属性值
        System.out.println(userEle.attributeValue("id"));

        // 7、先拿到元素对象，再提取值
        Element nameEle = userEle.element("name");
        System.out.println(nameEle.getText());
        System.out.println(nameEle.getTextTrim());

    }
}
