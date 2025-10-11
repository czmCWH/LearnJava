package com.czm.d2_xml;

public class Test1 {
    /*
     1、XML(全称 EXtensible Markup Language， 可扩展标记语言)
        本质是一种数据的格式，可以用来存储复杂的数据结构，和数据关系。

     2、特点：
        XML 中的 “<标签名>” 称为一个标签或一个元素，一般是成对出现的。
        XML 中的标签名可以自己定义(可扩展)，但必须要正确的嵌套。
        XML 中只能有一个根标签。
        XML 中的标签可以有属性
        如果一个文件中放置的是XML格式的数据，这个文件就是XML文件，后缀一般要写成.xml。

     3、XML文件的语法
        a、XML文件的后缀名为 .xml，文档声明必须是第一行
            文档声明：<?xml version="1.0" encoding="UTF-8" ?>
            version:XML默认的版本号码、该属性是必须存在的
            encoding:本XML文件的编码

        b、XML中书写特殊字符可能会出现冲突，导致报错，此时可以用如下特殊字符替代：
            &lt; < 小于
            &gt; > 大于
            &amp; & 和号
            &apos; ' 单引号
            &quot; " 引号

        c、XML中可以写一个叫CDATA的数据区：<![CDATA[...内容...]]>，里面的内容可以随便写，特殊字符也可以写
            输入：CD 回车即可匹配
     4、XML的作用和应用场景
        本质是一种数据格式，可以存储复杂的数据结构，和数据关系。
        应用场景：
	        a、经常用来做为软件的配置文件;
	        b、或者作为一种特殊的数据结构，在网络中进行传输；目前很少用
     */
    public static void main(String[] args) {

    }
}
